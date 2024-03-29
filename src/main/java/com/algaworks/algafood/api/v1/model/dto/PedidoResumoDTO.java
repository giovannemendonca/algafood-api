package com.algaworks.algafood.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoDTO  extends RepresentationModel<PedidoResumoDTO> {

  private String codigo;
  private BigDecimal subtotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;
  private String status;
  private OffsetDateTime dataCriacao;
  private RestauranteResumoDTO restaurante;
  private UsuarioDTO cliente;
}
