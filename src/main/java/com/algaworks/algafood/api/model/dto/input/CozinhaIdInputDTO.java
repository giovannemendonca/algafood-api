package com.algaworks.algafood.api.model.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInputDTO {

  @NotNull
  private Long id;
}
