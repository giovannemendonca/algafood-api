package com.algaworks.algafood.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO {

    private Long id;

    private String nome;

}
