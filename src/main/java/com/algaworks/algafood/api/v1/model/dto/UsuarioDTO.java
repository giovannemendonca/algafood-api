package com.algaworks.algafood.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios") // o Relation é usado para mudar o nome do atributo _embedded
@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

  private Long id;
  private String nome;
  private String email;

}
