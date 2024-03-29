package com.algaworks.algafood.api.v1.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDTO {

  @NotBlank
  private String nome;
}
