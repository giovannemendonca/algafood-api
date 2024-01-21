package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.PermissaoDTOAssembler;
import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

  @Autowired
  private CadastroGrupoService cadastroGrupoService;

  @Autowired
  private GrupoRepository grupoRepository;

  @Autowired
  private PermissaoDTOAssembler permissaoDTOAssembler;

  @Autowired
  private CadastroPermissaoService cadastroPermissaoService;

  @GetMapping
  public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
    Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
    return permissaoDTOAssembler.toCollectionDTO(grupo.getPermissoes());
  }

  @PutMapping("{permissaoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
    cadastroGrupoService.associarPermissao(grupoId, permissaoId);
  }

  @DeleteMapping("{permissaoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
    cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
  }
}
