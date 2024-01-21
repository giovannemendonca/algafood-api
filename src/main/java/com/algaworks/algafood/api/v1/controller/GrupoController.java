package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.GrupoDTOAssembrer;
import com.algaworks.algafood.api.v1.assembler.GrupoInputDTODisassembler;
import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import com.algaworks.algafood.api.v1.model.dto.input.GrupoInputDTO;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/grupos")
public class GrupoController {

  @Autowired
  private GrupoRepository grupoRepository;

  @Autowired
  private GrupoDTOAssembrer grupoDTOAssembrer;

  @Autowired
  private GrupoInputDTODisassembler grupoInputDTODisassembler;

  @Autowired
  private CadastroGrupoService cadastroGrupoService;

  @GetMapping
  public List<GrupoDTO> listar() {
    List<Grupo> grupos = grupoRepository.findAll();
    return grupoDTOAssembrer.toCollectionModel(grupos);
  }

  @GetMapping("{grupoId}")
  public GrupoDTO buscar(@PathVariable Long grupoId){
      Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
      return grupoDTOAssembrer.toDTO(grupo);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GrupoDTO adicionar(@Valid @RequestBody GrupoInputDTO grupoInputDTO){
    Grupo grupo = grupoInputDTODisassembler.toDomainObject(grupoInputDTO);
    Grupo grupoSalvo = cadastroGrupoService.salvar(grupo);
    return grupoDTOAssembrer.toDTO(grupoSalvo);
  }

  @PutMapping("{grupoId}")
  public GrupoDTO atualizar(@Valid @RequestBody GrupoInputDTO grupoInputDTO, @PathVariable Long grupoId){
    try {
      Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
      grupoInputDTODisassembler.copyToDomainObject(grupoInputDTO, grupoAtual);
      Grupo grupoAtualizado =  cadastroGrupoService.salvar(grupoAtual);
      return grupoDTOAssembrer.toDTO(grupoAtual);
    }catch (GrupoNaoEncontradoException e){
      throw new NegocioException(e.getMessage());
    }
  }

  @DeleteMapping("{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long grupoId){
    cadastroGrupoService.excluir(grupoId);
  }






}
