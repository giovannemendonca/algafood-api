package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {

  // essa classe é um factory de specifications
  // ela retorna specifications que podem ser usadas em queries
  public static Specification<Restaurante> comFreteGratis(){
    return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
  }

  // essa classe é um factory de specifications
  // ela retorna specifications que podem ser usadas em queries
  public static Specification<Restaurante> comNomeSemelhante(String nome){
    return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
  }
}
