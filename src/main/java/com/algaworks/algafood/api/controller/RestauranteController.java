package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteDTOAssembler;
import com.algaworks.algafood.api.assembler.RestauranteInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.dto.input.RestauranteInputDTO;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  private final RestauranteRepository restauranteRepository;
  private final CadastroRestauranteService cadastroRestaurante;
  private final RestauranteDTOAssembler restauranteDTOAssembler;
  private final RestauranteInputDTODisassembler restauranteInputDTODisassembler;


  RestauranteController(RestauranteRepository restauranteRepository,
                        CadastroRestauranteService cadastroRestaurante,
                        RestauranteDTOAssembler restauranteDTOAssembler,
                        RestauranteInputDTODisassembler restauranteInputDTODisassembler) {
    this.restauranteRepository = restauranteRepository;
    this.cadastroRestaurante = cadastroRestaurante;
    this.restauranteDTOAssembler = restauranteDTOAssembler;
    this.restauranteInputDTODisassembler = restauranteInputDTODisassembler;
  }

  @GetMapping
  public List<RestauranteDTO> listar() {
    List<Restaurante> restaurantes = restauranteRepository.findAll();
    return restauranteDTOAssembler.toCollectionModel(restaurantes);
  }

  @GetMapping("/{restauranteId}")
  public RestauranteDTO buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
    return restauranteDTOAssembler.toDTO(restaurante);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
    try {

      Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInputDTO);
      Restaurante RestauranteCadastrado = cadastroRestaurante.salvar(restaurante);
      return restauranteDTOAssembler.toDTO(RestauranteCadastrado);
    }catch (CozinhaNaoEncontradoException e){
      throw new NegocioException(e.getMessage());
    }
  }

  @PutMapping("/{restauranteId}")
  public RestauranteDTO atualizar(@PathVariable Long restauranteId, @Valid @RequestBody RestauranteInputDTO restauranteInputDTO) {

    try {
      Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

      // não precisa retornar o objeto, pois o objeto já está sendo passado por referência
      restauranteInputDTODisassembler.copyToDomainObject(restauranteInputDTO, restauranteAtual);

      Restaurante restauranteCadastrado = cadastroRestaurante.salvar(restauranteAtual);
      return restauranteDTOAssembler.toDTO(restauranteCadastrado);
    }catch (CozinhaNaoEncontradoException e){
      throw new NegocioException(e.getMessage());
    }

  }

  @PutMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativar(@PathVariable Long restauranteId) {
    cadastroRestaurante.ativar(restauranteId);
  }

  @PutMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativarMultiplos(@RequestBody List<Long> restaurantesIds) {
    try {
      cadastroRestaurante.ativar(restaurantesIds);
    }catch (RestauranteNaoEncontradoException e){
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @DeleteMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativar(@PathVariable Long restauranteId) {
    cadastroRestaurante.inativar(restauranteId);
  }

  @DeleteMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativarMultiplos(@RequestBody List<Long> restaurantesIds) {
    try {
      cadastroRestaurante.inativar(restaurantesIds);
    }catch (RestauranteNaoEncontradoException e){
      throw new NegocioException(e.getMessage(),e);
    }
  }

  @PutMapping("/{restauranteId}/abertura")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void abrir(@PathVariable Long restauranteId){
    cadastroRestaurante.abrir(restauranteId);
  }

  @PutMapping("/{restauranteId}/fechamento")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void fechar(@PathVariable Long restauranteId){
    cadastroRestaurante.fechar(restauranteId);
  }


}
