package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

import java.io.InputStream;

@Service
public class CatalogoFotoProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FotoStorageService fotoStorage;

    CatalogoFotoProdutoService(ProdutoRepository produtoRepository, FotoStorageService fotoStorageService) {
        this.produtoRepository = produtoRepository;
        this.fotoStorage = fotoStorageService;
    }

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivos) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        var fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }
        foto.setNomeArquivo(nomeNovoArquivo);

        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(dadosArquivos)
                .build();

        fotoStorage.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }

    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        FotoProduto fotoProduto = buscarOuFalhar(restauranteId, produtoId);
        produtoRepository.delete(fotoProduto);
        produtoRepository.flush();

        fotoStorage.remover(fotoProduto.getNomeArquivo());
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId){
        return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(restauranteId, produtoId)
        );
    }
}
