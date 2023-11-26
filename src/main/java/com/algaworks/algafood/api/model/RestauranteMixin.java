package com.algaworks.algafood.api.model;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {

  @JsonIgnoreProperties(value = "nome", allowGetters = true)
  private Cozinha cozinha;

  @JsonIgnore
  private Endereco endereco;

  //@JsonIgnore
  private OffsetDateTime dataCadastro;

  //@JsonIgnore
  private OffsetDateTime dataAtualizacao;

  @JsonIgnore
  private List<FormaPagamento> formasPagamento = new ArrayList<>();

  @JsonIgnore
  List<Produto> produtos = new ArrayList<>();
}
