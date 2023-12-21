package com.algaworks.algafood.infrastructure.service.storage;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class S3FotosStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;


    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {

            String bucket = storageProperties.getS3().getBucket();
            String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(novaFoto.getContentType());

            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, caminhoArquivo, novaFoto.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar arquivos para Amazon S3.", e);
        }

    }


    @Override
    public void remover(String nomeArquivo) {
        try {

            String bucket = storageProperties.getS3().getBucket();
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, caminhoArquivo);
            amazonS3.deleteObject(deleteObjectRequest);

        } catch (Exception e) {
            throw new StorageException("Não foi possível remover arquivos da Amazon S3.", e);
        }

    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {

        try {
            String bucket = storageProperties.getS3().getBucket();
            String caminhoArquivo = getCaminhoArquivo(nomeArquivo);

            URL url = amazonS3.getUrl(bucket, caminhoArquivo);

            return FotoRecuperada.builder()
                    .url(url.toString())
                    .build();

        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivos da Amazon S3.", e);
        }
    }


    private String getCaminhoArquivo(String nomeArquivo) {
        var diretorioFotos = storageProperties.getS3().getDiretorioFotos();
        return String.format("%s/%s", diretorioFotos, nomeArquivo);
    }
}
