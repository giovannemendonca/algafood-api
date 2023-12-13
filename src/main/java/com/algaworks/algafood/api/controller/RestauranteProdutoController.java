package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.ProdutoDTOAssembler;
import com.algaworks.algafood.api.assembler.ProdutoInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.model.dto.input.ProdutoInputDTO;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {


  @Autowired
  private CadastroProdutoService cadastroProdutoService;

  @Autowired
  private CadastroRestauranteService cadastroRestauranteService;

  @Autowired
  private ProdutoRepository produtoRepository;


  @Autowired
  private ProdutoDTOAssembler produtoDTOAssembler;

  @Autowired
  private ProdutoInputDTODisassembler produtoInputDTODisassembler;


  @GetMapping
  public List<ProdutoDTO> listar(
          @PathVariable Long restauranteId,
          @RequestParam(required = false) boolean incluirInativos){

    Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

    List<Produto> produtos = null;

    if (incluirInativos){
      produtos = produtoRepository.findTodosByRestaurante(restaurante);
    } else {
      produtos = produtoRepository.findAtivosByRestaurante(restaurante);
    }
    return produtoDTOAssembler.toCollectionModel(produtos);
  }

  @GetMapping("/{produtoId}")
  public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
    Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

    return produtoDTOAssembler.toDTO(produto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProdutoDTO adicionar(@Valid @RequestBody ProdutoInputDTO produtoInputDTO, @PathVariable Long restauranteId){
    Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

    Produto produto = produtoInputDTODisassembler.toDomainObject(produtoInputDTO);

    produto.setRestaurante(restaurante);

    var produtoSalvo = cadastroProdutoService.salvar(produto);

    return produtoDTOAssembler.toDTO(produtoSalvo);
  }


  @PutMapping("/{produtoId}")
  public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInputDTO produtoInput) {

    Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
    produtoInputDTODisassembler.copyToDomainObject(produtoInput, produtoAtual);
    produtoAtual = cadastroProdutoService.salvar(produtoAtual);

    return produtoDTOAssembler.toDTO(produtoAtual);
  }



}
