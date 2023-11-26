package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaDTOAssembler;
import com.algaworks.algafood.api.assembler.CozinhaInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.model.dto.input.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Autowired
  private CozinhaDTOAssembler cozinhaDTOAssembler;

  @Autowired
  private CozinhaInputDTODisassembler cozinhaInputDTODisassembler;

  @GetMapping
  public List<CozinhaDTO> listar() {
    List<Cozinha> cozinhas = cozinhaRepository.findAll();
    return cozinhaDTOAssembler.toCollectionModel(cozinhas);
  }

  @GetMapping("/{cozinhaId}")
  public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
    Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
    CozinhaDTO cozinhaDTO = cozinhaDTOAssembler.toModel(cozinha);
    return cozinhaDTO;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO) {
    Cozinha cozinha = cozinhaInputDTODisassembler.toDomainObject(cozinhaInputDTO);
    CozinhaDTO cozinhaDTO = cozinhaDTOAssembler.toModel(cadastroCozinha.salvar(cozinha));
    return cozinhaDTO;
  }

  @PutMapping("/{cozinhaId}")
  public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInputDTO cozinhaInputDTO) {

    Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

    cozinhaInputDTODisassembler.copyToDomainObject(cozinhaInputDTO, cozinhaAtual);
    cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
    CozinhaDTO cozinhaDTO = cozinhaDTOAssembler.toModel(cozinhaAtual);

    return cozinhaDTO;
  }

  @DeleteMapping("/{cozinhaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long cozinhaId) {
    cadastroCozinha.excluir(cozinhaId);
  }

}
