package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.api.assembler.CidadeDTOAssembler;
import com.algaworks.algafood.api.assembler.CidadeInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.CidadeDTO;
import com.algaworks.algafood.api.model.dto.input.CidadeInputDTO;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private CadastroCidadeService cadastroCidade;

  @Autowired
  private CidadeDTOAssembler cidadeDTOAssembler;

  @Autowired
  private CidadeInputDTODisassembler cidadeInputDTODisassembler;

  @GetMapping
  private List<CidadeDTO> listar() {
    List<Cidade> cidades = cidadeRepository.findAll();
    List<CidadeDTO> cidadesDTO = cidadeDTOAssembler.toCollectionDTO(cidades);
    return cidadesDTO;
  }

  @GetMapping("/{cidadeId}")
  private CidadeDTO buscar(@PathVariable Long cidadeId) {
    Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
    CidadeDTO cidadeDTO = cidadeDTOAssembler.toDTO(cidade);

    return cidadeDTO;
  }

  @PostMapping
  private CidadeDTO adicionar(@Valid @RequestBody CidadeInputDTO cidadeInputDTO) {
    try {
      Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInputDTO);
      cidade = cadastroCidade.salvar(cidade);
      CidadeDTO cidadeDTO = cidadeDTOAssembler.toDTO(cidade);
      return cidadeDTO;
    } catch (EstadoNaoEncontradoException e) {
      throw new NegocioException(e.getMessage());
    }
  }

  @PutMapping("/{cidadeId}")
  private CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody CidadeInputDTO cidadeInputDTO) {
    try {
      Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
      cidadeInputDTODisassembler.copyToDomainObject(cidadeInputDTO, cidadeAtual);

      cidadeAtual = cadastroCidade.salvar(cidadeAtual);
      CidadeDTO cidadeDTO = cidadeDTOAssembler.toDTO(cidadeAtual);

      return cidadeDTO;
    } catch (EstadoNaoEncontradoException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @DeleteMapping("/{cidadeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  private void excluir(@PathVariable Long cidadeId) {
      cadastroCidade.excluir(cidadeId);
  }


}
