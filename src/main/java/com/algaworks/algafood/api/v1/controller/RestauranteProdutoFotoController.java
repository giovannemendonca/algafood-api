package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.FotoProdutoDTOAssembler;
import com.algaworks.algafood.api.v1.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.v1.model.dto.input.FotoProdutoInputDTO;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.domain.service.FotoStorageService.FotoRecuperada;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    private CatalogoFotoProdutoService catalogoFotoProdutoService;
    private CadastroProdutoService cadastroProdutoService;
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;
    private FotoStorageService fotoStorageService;

    RestauranteProdutoFotoController(CatalogoFotoProdutoService catalogoFotoProdutoService,
                                     CadastroProdutoService cadastroProdutoService,
                                     FotoProdutoDTOAssembler fotoProdutoDTOAssembler,
                                     FotoStorageService fotoStorageService
    ) {
        this.catalogoFotoProdutoService = catalogoFotoProdutoService;
        this.cadastroProdutoService = cadastroProdutoService;
        this.fotoProdutoDTOAssembler = fotoProdutoDTOAssembler;
        this.fotoStorageService = fotoStorageService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
        return fotoProdutoDTOAssembler.toDTO(fotoProduto);
    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId,
                                        @Valid FotoProdutoInputDTO fotoProdutoInput
    ) throws IOException {
        MultipartFile arquivo = fotoProdutoInput.getArquivo();
        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());

        return fotoProdutoDTOAssembler.toDTO(fotoSalva);
    }

    @GetMapping
    public ResponseEntity<?> servir(@PathVariable Long restauranteId,
                                    @PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        catalogoFotoProdutoService.excluir(restauranteId, produtoId);
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> mediaTypesAceitas)
            throws HttpMediaTypeNotAcceptableException {

        // anyMatch retorna true se pelo menos um elemento satisfaz a condição
        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypesAceita -> mediaTypesAceita.isCompatibleWith(mediaType));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }
}
