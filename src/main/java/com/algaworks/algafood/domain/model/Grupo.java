package com.algaworks.algafood.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @ManyToMany
  @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"),
          inverseJoinColumns = @JoinColumn(name = "permissao_id"))
  private Set<Permissao> permissoes = new HashSet<>();


  public boolean removerPermissoes(Permissao permissao){
    return getPermissoes().remove(permissao);
  }
  public boolean adicionarPermissoes(Permissao permissao){
    return getPermissoes().add(permissao);
  }

}
