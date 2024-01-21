package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.api.v1.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

  @Autowired
  private CadastroRestauranteService cadastroRestauranteService;

  @Autowired
  private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;


  @GetMapping
  public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
    return formaPagamentoDTOAssembler.toCollectionDTO(restaurante.getFormasPagamento());
  }

  @PutMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
    cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
  }

  @DeleteMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
    cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
  }

}
