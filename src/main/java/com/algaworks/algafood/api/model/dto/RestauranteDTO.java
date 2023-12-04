package com.algaworks.algafood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class RestauranteDTO {


  private Long id;
  private String nome;
  private BigDecimal taxaFrete;
  private CozinhaDTO cozinha;
  private Boolean ativo;
  private Boolean aberto;
  private EnderecoDTO endereco;
}
