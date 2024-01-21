package com.algaworks.algafood.api.v1.model.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInputDTO {

  @NotNull
  private Long id;
}
