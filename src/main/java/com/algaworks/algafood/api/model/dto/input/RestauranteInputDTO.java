package com.algaworks.algafood.api.model.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteInputDTO {

  @NotBlank
  private  String nome;

  @PositiveOrZero
  @NotNull
  private BigDecimal taxaFrete;

  @Valid
  @NotNull
  private CozinhaIdInputDTO cozinha;

  @Valid
  @NotNull
  private EnderecoInputDTO endereco;
}
