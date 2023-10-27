package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
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

  @GetMapping
  private List<Cidade> listar() {
    return cidadeRepository.findAll();
  }

  @GetMapping("/{cidadeId}")
  private Cidade buscar(@PathVariable Long cidadeId) {
    return cadastroCidade.buscarOuFalhar(cidadeId);
  }

  @PostMapping
  private Cidade adicionar(@RequestBody Cidade cidade) {
    System.out.println(cidade);
    return cadastroCidade.salvar(cidade);
  }

  @PutMapping("/{cidadeId}")
  private Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
    Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
      BeanUtils.copyProperties(cidade, cidadeAtual, "id");
    return cadastroCidade.salvar(cidadeAtual);
  }

  @DeleteMapping("/{cidadeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  private void excluir(@PathVariable Long cidadeId) {
      cadastroCidade.excluir(cidadeId);
  }


}
