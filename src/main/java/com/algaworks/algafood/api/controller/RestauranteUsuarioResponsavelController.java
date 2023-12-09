package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.api.assembler.UsuarioDTOAssembler;
import com.algaworks.algafood.api.model.dto.UsuarioDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

  private CadastroRestauranteService cadastroRestauranteService;
  private UsuarioDTOAssembler usuarioDTOAssembler;

  RestauranteUsuarioResponsavelController(
          CadastroRestauranteService cadastroRestaurante,
          UsuarioDTOAssembler usuarioDTOAssembler
          ) {
    this.cadastroRestauranteService = cadastroRestaurante;
    this.usuarioDTOAssembler = usuarioDTOAssembler;
  }

  @GetMapping
  public List<UsuarioDTO> listar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
    Set<Usuario> usuarios = restaurante.getResponsaveis();
    return usuarioDTOAssembler.toCollectionModel(usuarios);
  }

  @DeleteMapping("/{usuarioId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
    cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
  }

  @PutMapping("/{usuarioId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
    cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
  }

}
