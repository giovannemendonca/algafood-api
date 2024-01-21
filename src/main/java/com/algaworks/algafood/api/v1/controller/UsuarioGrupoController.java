package com.algaworks.algafood.api.v1.controller;


import com.algaworks.algafood.api.v1.assembler.GrupoDTOAssembrer;
import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

  @Autowired
  private CadastroUsuarioService cadastroUsuarioService;

  @Autowired
  private GrupoDTOAssembrer grupoDTOAssembrer;

  @GetMapping
  public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
    Usuario usuarios = cadastroUsuarioService.buscarOuFalhar(usuarioId);
    return grupoDTOAssembrer.toCollectionModel(usuarios.getGrupos());
  }

  @PutMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
    cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
  }

  @DeleteMapping("/{grupoId}")
  public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
    cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
  }



}
