package com.algaworks.algafood.api.v1.model.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDTO {

  @NotNull
  private String cep;

  @NotBlank
  private String logradouro;

  @NotBlank
  private String numero;

  private String complemento;

  @NotBlank
  private String bairro;

  @NotNull
  @Valid
  private CidadeIdInputDTO cidade;

}
