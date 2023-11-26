package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private BigDecimal subtotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;

  @CreationTimestamp
  private OffsetDateTime dataCriacao;

  private OffsetDateTime dataConfirmacao;
  private OffsetDateTime dataCancelamento;
  private OffsetDateTime dataEntrega;

  @Embedded
  private Endereco enderecoEntrega;

  private StatusPedido statusPedido;

  @ManyToOne // o ManyToOne diz que um pedido tem uma forma de pagamento ou seja uma forma de pagamento tem muitos pedidos
  @JoinColumn(nullable = false)
  private FormaPagamento formaPagamento;

  @ManyToOne // o ManyToOne diz que um pedido tem um restaurante ou seja um restaurante tem muitos pedidos
  @JoinColumn(nullable = false)
  private Restaurante restaurante;

  @ManyToOne // o ManyToOne diz que um pedido tem um cliente ou seja um cliente tem muitos pedidos
  @JoinColumn(name = "usuario_cliente_id", nullable = false)
  private Usuario usuario;

  @OneToMany(mappedBy = "pedido") // o OneToMany diz que um pedido tem muitos itens ou seja um item tem um pedido
  private List<ItemPedido> itens = new ArrayList<>();

}
