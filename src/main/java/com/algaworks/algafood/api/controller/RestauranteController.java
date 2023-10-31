package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.Groups;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteRepository restauranteRepository;
  @Autowired
  private CadastroRestauranteService cadastroRestaurante;


  @GetMapping
  public List<Restaurante> listar() {
    return restauranteRepository.findAll();
  }


  @GetMapping("/{restauranteId}")
  public Restaurante buscar(@PathVariable Long restauranteId) {

    return cadastroRestaurante.buscarOuFalhar(restauranteId);
  }


  @PostMapping
  public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
    try {
      return cadastroRestaurante.salvar(restaurante);
    }catch (CozinhaNaoEncontradoException e){
      throw new NegocioException(e.getMessage());
    }
  }


  @PutMapping("/{restauranteId}")
  public Restaurante atualizar(@PathVariable Long restauranteId, @Valid @RequestBody Restaurante restaurante) {

    Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
    BeanUtils.copyProperties(restaurante,
            restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

    try {
      return cadastroRestaurante.salvar(restauranteAtual);
    }catch (CozinhaNaoEncontradoException e){
      throw new NegocioException(e.getMessage());
    }

  }


  @PatchMapping("/{restauranteId}")
  public Restaurante atualiazarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request) {

    Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
    merge(campos, restauranteAtual, request);

    return atualizar(restauranteId, restauranteAtual);
  }


  private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

    ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);


      // O método convertValue() converte um objeto (dadosOrigem) para o tipo especificado (Restaurante.class)
      // exemplo: (dadosOrigem, Restaurante.class)
      Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

      // Map.Entry é uma interface que representa um par de chave e valor em um Map (chave, valor)
      // O método entrySet() retorna um conjunto de pares (chave, valor) contidos no Map
      for (Map.Entry<String, Object> entry : dadosOrigem.entrySet()) {

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
    } catch (IllegalArgumentException e) {
      Throwable routCause = ExceptionUtils.getRootCause(e);
      throw new HttpMessageNotReadableException(e.getMessage(), routCause, serverHttpRequest);
    }

  }
}
