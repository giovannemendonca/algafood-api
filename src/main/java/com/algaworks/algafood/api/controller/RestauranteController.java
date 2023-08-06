package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  RestauranteRepository restauranteRepository;
  @Autowired
  CadastroRestauranteService cadastroRestaurante;

  @GetMapping
  public List<Restaurante> listar() {
    return  restauranteRepository.listar();
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> buscar( @PathVariable Long restauranteId){
   Restaurante restaurante = restauranteRepository.buscar(restauranteId);
   if(restaurante != null){
     return ResponseEntity.ok(restaurante);
   }
   return ResponseEntity.notFound().build();
  }
  
  @PostMapping
  public ResponseEntity<?> adicionar( @RequestBody  Restaurante restaurante){
   try {
     restaurante = cadastroRestaurante.salvar(restaurante);
     return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
   }catch (EntidadeNaoEncontradaException e){
     return ResponseEntity
             .badRequest()
             .body(e.getMessage());
   }
  }

  @PutMapping("/{restauranteId}")
  public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){

    try {
      Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
      if(restauranteAtual != null){
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
        return ResponseEntity.ok(restauranteAtual);
      }
      return ResponseEntity.notFound().build();

    }catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }


  }

  @PatchMapping("/{restauranteId}")
  public ResponseEntity<?> atualiazarParcial(@PathVariable Long restauranteId, @RequestBody Map<String,Object> campos){

    Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
    if(restauranteAtual == null){
      return ResponseEntity.notFound().build();
    }
    merge(campos, restauranteAtual);

    return atualizar(restauranteId, restauranteAtual);
  }

  
  private static void merge( Map <String, Object> dadosOrigem, Restaurante restauranteDestino) {

    ObjectMapper objectMapper = new ObjectMapper();

    // O método convertValue() converte um objeto (dadosOrigem) para o tipo especificado (Restaurante.class)
    // exemplo: (dadosOrigem, Restaurante.class)
    Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

    // Map.Entry é uma interface que representa um par de chave e valor em um Map (chave, valor)
    // O método entrySet() retorna um conjunto de pares (chave, valor) contidos no Map
    for(Map.Entry <String, Object> entry : dadosOrigem.entrySet()) {

      String nomePropriedade = entry.getKey();
      Object valorPropriedade = entry.getValue();

      // Field é uma classe do pacote java.lang.reflect que representa um campo de uma classe
      // A classe ReflectionUtils é uma classe utilitária do Spring que fornece métodos para trabalhar com reflexão
      Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
      field.setAccessible(true);

      // O método getField() retorna o valor do campo representado (field) do objeto (restauranteOrigem)
      // exemplo: (nome, restauranteOrigem)
      // exemplo: (taxaFrete, restauranteOrigem)
      Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

      // o método setField() recebe 3 parâmetros: que são (filed, objeto, valor)
      // O método setField() atribui o valor (novoValor) ao campo representado (field) do objeto (restauranteDestino)
      ReflectionUtils.setField(field, restauranteDestino, novoValor);
    }
  }
}
