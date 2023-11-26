package com.algaworks.algafood.api.model.dto.input;

import com.algaworks.algafood.api.model.dto.EstadoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInputDTO {


  @NotBlank
  private String nome;

  @Valid
  @NotNull
  private EstadoIdInputDTO estado;
}
