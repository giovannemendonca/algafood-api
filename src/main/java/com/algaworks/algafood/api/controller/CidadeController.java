package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  private List<Cidade> listar(){
    return cidadeRepository.listar();
  }

  @GetMapping("/{cidadeId}")
  private ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
    Cidade cidade = cidadeRepository.buscar(cidadeId);

    if(cidade != null){
      return ResponseEntity.ok(cidade);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  private ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
    try {
      cidade = cadastroCidade.salvar(cidade);
      return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

    }catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{cidadeId}")
  private ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
    Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

    if(cidadeAtual != null){

      BeanUtils.copyProperties(cidade, cidadeAtual, "id");
      cidadeAtual = cadastroCidade.salvar(cidadeAtual);

      return ResponseEntity.ok(cidadeAtual);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{cidadeId}")
  private ResponseEntity<?> excluir(@PathVariable Long cidadeId){
    try {
      cadastroCidade.excluir(cidadeId);
      return ResponseEntity.noContent().build();

    }catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.notFound().build();

    }catch (EntidadeEmUsoException e){
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
  

}
