package com.algaworks.algafood.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "itens")
@Getter
@Setter
public class ItemPedidoDTO  extends RepresentationModel<ItemPedidoDTO> {
  private Long produtoId;
  private String produtoNome;
  private Integer quantidade;
  private BigDecimal precoUnitario;
  private BigDecimal precoTotal;
  private String observacao;
}
