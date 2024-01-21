package com.algaworks.algafood.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "enderecos")
@Getter
@Setter
public class EnderecoDTO extends RepresentationModel<EnderecoDTO> {

  private String cep;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private CidadeResumoDTO cidade;

}
