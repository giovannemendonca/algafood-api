package com.algaworks.algafood.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDTO {

  Long id;

  String descricao;
}
