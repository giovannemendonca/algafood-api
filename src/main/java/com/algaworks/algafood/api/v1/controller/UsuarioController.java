package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.UsuarioDTOAssembler;
import com.algaworks.algafood.api.v1.assembler.UsuarioInputDTODisassembler;
import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.v1.model.dto.input.SenhaInputDTO;
import com.algaworks.algafood.api.v1.model.dto.input.UsuarioComSenhaInputDTO;
import com.algaworks.algafood.api.v1.model.dto.input.UsuarioInputDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioDTOAssembler usuarioDTOAssembler;

  @Autowired
  private UsuarioInputDTODisassembler usuarioInputDTODisassembler;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private CadastroUsuarioService cadastroUsuarioService;


  @GetMapping
  public CollectionModel<UsuarioDTO> listar() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return usuarioDTOAssembler.toCollectionModel(usuarios);
  }

  @GetMapping("{usuarioId}")
  public UsuarioDTO buscar(@PathVariable Long usuarioId) {
    Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
    return usuarioDTOAssembler.toModel(usuario);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO cadastrar(@Valid @RequestBody UsuarioComSenhaInputDTO usuarioComSenhaInputDTO) {
    Usuario usuario = usuarioInputDTODisassembler.toDomainObject(usuarioComSenhaInputDTO);
    var usuarioCadastrado = cadastroUsuarioService.salvar(usuario);
    return usuarioDTOAssembler.toModel(usuarioCadastrado);
  }

  @PutMapping("{usuarioId}")
  public UsuarioDTO atualizar(@Valid @RequestBody UsuarioInputDTO usuarioInputDTO, @PathVariable Long usuarioId) {

    Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
    usuarioInputDTODisassembler.copyToDomainObject(usuarioInputDTO, usuarioAtual);
    var usuarioSalvo = cadastroUsuarioService.salvar(usuarioAtual);

    return usuarioDTOAssembler.toModel(usuarioSalvo);
  }

  @PutMapping("{usuarioId}/senha")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void alterarSenha(@PathVariable Long usuarioId, @Valid @RequestBody SenhaInputDTO senhaInputDTO) {
    cadastroUsuarioService.alterarSenha(usuarioId, senhaInputDTO.getSenhaAtual(), senhaInputDTO.getNovaSenha());
  }


}
