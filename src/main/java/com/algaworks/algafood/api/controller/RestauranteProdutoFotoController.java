package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FotoProdutoDTOAssembler;
import com.algaworks.algafood.api.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.model.dto.input.FotoProdutoInputDTO;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    private CatalogoFotoProdutoService catalogoFotoProdutoService;
    private CadastroProdutoService cadastroProdutoService;
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    RestauranteProdutoFotoController(CatalogoFotoProdutoService catalogoFotoProdutoService,
                                     CadastroProdutoService cadastroProdutoService,
                                     FotoProdutoDTOAssembler fotoProdutoDTOAssembler) {
        this.catalogoFotoProdutoService = catalogoFotoProdutoService;
        this.cadastroProdutoService = cadastroProdutoService;
        this.fotoProdutoDTOAssembler = fotoProdutoDTOAssembler;
    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId,
                                        @Valid FotoProdutoInputDTO fotoProdutoInput
    ) {
        MultipartFile arquivo = fotoProdutoInput.getArquivo();
        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);

        return fotoProdutoDTOAssembler.toDTO(fotoSalva);

    }
}
