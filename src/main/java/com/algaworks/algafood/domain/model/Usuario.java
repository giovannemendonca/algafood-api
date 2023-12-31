package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private OffsetDateTime dataCadastro;

  @ManyToMany
  @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
          inverseJoinColumns = @JoinColumn(name = "grupo_id"))
  private Set<Grupo> grupos = new HashSet<>();

  public boolean senhaCoincidemCom(String senha){
   return getSenha().equals(senha);
  }

  public boolean senhaNaoCoincidemCom(String senhaAutal){
    return !senhaCoincidemCom(senhaAutal);
  }

  public boolean removerGrupo(Grupo grupo){
    return getGrupos().remove(grupo);
  }

  public boolean adicionarGrupo(Grupo grupo){
    return getGrupos().add(grupo);
  }

}
