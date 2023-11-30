package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteDTOAssembler;
import com.algaworks.algafood.api.assembler.RestauranteInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.dto.input.RestauranteInputDTO;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService cadastroRestaurante;

  @Autowired
  private SmartValidator validator;

  @Autowired
  private RestauranteDTOAssembler restauranteDTOAssembler;

  @Autowired
  private RestauranteInputDTODisassembler restauranteInputDTODisassembler;


  @GetMapping
  public List<RestauranteDTO> listar() {
    List<Restaurante> restaurantes = restauranteRepository.findAll();
    List<RestauranteDTO> restaurantesDTO = restauranteDTOAssembler.toCollectionModel(restaurantes);

    return restaurantesDTO;
  }

  @GetMapping("/{restauranteId}")
  public RestauranteDTO buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
    RestauranteDTO restauranteDTO = restauranteDTOAssembler.toDTO(restaurante);

    return restauranteDTO;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
    try {

      Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInputDTO);
      Restaurante RestauranteCadastrado = cadastroRestaurante.salvar(restaurante);
      RestauranteDTO restauranteDTO = restauranteDTOAssembler.toDTO(RestauranteCadastrado);
      return restauranteDTO;
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
      RestauranteDTO restauranteDTO = restauranteDTOAssembler.toDTO(restauranteCadastrado);
      return restauranteDTO;
    }catch (CozinhaNaoEncontradoException e){
      throw new NegocioException(e.getMessage());
    }

  }

  @PutMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativar(@PathVariable Long restauranteId) {
    cadastroRestaurante.ativar(restauranteId);
  }

  @DeleteMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativar(@PathVariable Long restauranteId) {
    cadastroRestaurante.inativar(restauranteId);
  }

}
