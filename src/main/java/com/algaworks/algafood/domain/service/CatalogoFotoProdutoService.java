package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    private final ProdutoRepository produtoRepository;

    CatalogoFotoProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {


        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        var fotoExistente = produtoRepository
                .findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }

        return produtoRepository.save(foto);
    }
}
