package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
  private LocalDateTime dataCriacao;

  private LocalDateTime dataConfirmacao;
  private LocalDateTime dataCancelamento;
  private LocalDateTime dataEntrega;

  @Embedded
  private Endereco enderecoEntrega;

  private StatusPedido statusPedido;

  @ManyToOne
  @JoinColumn(nullable = false)
  private FormaPagamento formaPagamento;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurante restaurante;

  @ManyToOne
  @JoinColumn(name = "usuario_cliente_id", nullable = false)
  private Usuario usuario;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens = new ArrayList<>();

}
