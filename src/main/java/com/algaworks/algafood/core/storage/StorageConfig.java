package com.algaworks.algafood.core.storage;

import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.core.storage.StorageProperties.TipoStorage;
import com.algaworks.algafood.infrastructure.service.storage.LocalFotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.S3FotosStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;


    @Bean
    public AmazonS3 amazonS3() {

        var idChaveAcesso = storageProperties.getS3().getIdChaveAcesso();
        var chaveAcessoSecreta = storageProperties.getS3().getChaveAcessoSecreta();
        var regiao = storageProperties.getS3().getRegiao();

        var credentials = new BasicAWSCredentials(idChaveAcesso, chaveAcessoSecreta);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(regiao)
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (TipoStorage.S3.equals(storageProperties.getTipo())) {
            return new S3FotosStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }
}
