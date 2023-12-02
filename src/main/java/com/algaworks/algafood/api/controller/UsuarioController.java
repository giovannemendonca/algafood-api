package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioDTOAssembler;
import com.algaworks.algafood.api.assembler.UsuarioInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.model.dto.input.SenhaInputDTO;
import com.algaworks.algafood.api.model.dto.input.UsuarioComSenhaInputDTO;
import com.algaworks.algafood.api.model.dto.input.UsuarioInputDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
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
  public List<UsuarioDTO> listar() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    return usuarioDTOAssembler.toCollectionModel(usuarios);
  }

  @GetMapping("{usuarioId}")
  public UsuarioDTO buscar(@PathVariable Long usuarioId) {
    Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
    return usuarioDTOAssembler.toDTO(usuario);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO cadastrar(@Valid @RequestBody UsuarioComSenhaInputDTO usuarioComSenhaInputDTO) {
    Usuario usuario = usuarioInputDTODisassembler.toDomainObject(usuarioComSenhaInputDTO);
    var usuarioCadastrado = cadastroUsuarioService.salvar(usuario);
    return usuarioDTOAssembler.toDTO(usuarioCadastrado);
  }

  @PutMapping("{usuarioId}")
  public UsuarioDTO atualizar(@Valid @RequestBody UsuarioInputDTO usuarioInputDTO, @PathVariable Long usuarioId) {

    Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
    usuarioInputDTODisassembler.copyToDomainObject(usuarioInputDTO, usuarioAtual);
    var usuarioSalvo = cadastroUsuarioService.salvar(usuarioAtual);

    return usuarioDTOAssembler.toDTO(usuarioSalvo);
  }

  @PutMapping("{usuarioId}/senha")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void alterarSenha(@PathVariable Long usuarioId, @Valid @RequestBody SenhaInputDTO senhaInputDTO) {
    cadastroUsuarioService.alterarSenha(usuarioId, senhaInputDTO.getSenhaAtual(), senhaInputDTO.getNovaSenha());
  }


}
