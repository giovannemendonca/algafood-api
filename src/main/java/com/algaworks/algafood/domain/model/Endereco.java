package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;

// A anotação @Embeddable indica que a classe Endereco será um componente de outra entidade.

@Data
@Embeddable
public class Endereco {

  @Column(name = "endereco_cep")
  private String cep;

  @Column(name = "endereco_logradouro")
  private String logradouro;

  @Column(name = "endereco_numero")
  private String numero;

  @Column(name = "endereco_complemento")
  private String complemento;

  @Column(name = "endereco_bairro")
  private String bairro;

  @ManyToOne
  @JoinColumn(name = "endereco_cidade_id")
  private Cidade Cidade;


}
