package com.algaworks.algafood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoResumoDTO {

  private String codigo;
  private BigDecimal subtotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;
  private String status;
  private OffsetDateTime dataCriacao;
  private RestauranteResumoDTO restaurante;
  private UsuarioDTO cliente;
}
