package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CatalogoFotoProdutoService {

    private ProdutoRepository produtoRepository;

    CatalogoFotoProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto){
        return produtoRepository.save(fotoProduto);
    }
}
