package com.algaworks.algafood.api.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

    private Long id;

    private String nome;

    private EstadoDTO estado;

}
