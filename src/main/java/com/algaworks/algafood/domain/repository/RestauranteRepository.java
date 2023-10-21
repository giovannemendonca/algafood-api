package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {
 
  @Query("from Restaurante r join fetch r.cozinha")
  List<Restaurante> findAll();

  List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

  List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

  Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

  List<Restaurante> findTop2ByNomeContaining(String nome);

  int countByCozinhaId(Long cozinha);

}
