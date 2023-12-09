package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {


  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Autowired
  private CadastroCidadeService cadastroCidadeService;

  @Autowired
  private CadastroFormaPagamentoService cadastroFormaPagamentoService;

  @Autowired
  private CadastroUsuarioService cadastroUsuarioService;


  @Transactional
  public Restaurante salvar(Restaurante restaurante){

    Long cozinhaId = restaurante.getCozinha().getId();
    Long cidadeId = restaurante.getEndereco().getCidade().getId();

    Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
    Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

    restaurante.setCozinha(cozinha);
    restaurante.getEndereco().setCidade(cidade);

    return restauranteRepository.save(restaurante);
  }

  @Transactional
  public void ativar(Long restauranteId){
    Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

    restauranteAtual.ativar();

    // não precisa do save, pois o objeto já está sendo gerenciado pelo JPA
  }

  @Transactional
  public void inativar(Long restauranteId){
    Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

    restauranteAtual.inativar();

    // não precisa do save, pois o objeto já está sendo gerenciado pelo JPA
  }

  @Transactional
  public void abrir(Long restauranteId){
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    restaurante.abrir();
  }

  @Transactional
  public void fechar(Long restauranteId){
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    restaurante.fechar();
  }

  @Transactional
  public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

    restaurante.removerFormaPagamento(formaPagamento);
  }

  @Transactional
  public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

    restaurante.adicionarFormaPagamento(formaPagamento);
  }

  public Restaurante buscarOuFalhar(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
            .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
  }

  @Transactional
  public void desassociarResponsavel(Long restauranteId, Long usuarioId){
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
    restaurante.removerResponsavel(usuario);
  }

  @Transactional
  public void associarResponsavel(Long restauranteId, Long usuarioId) {
    Restaurante restaurante = buscarOuFalhar(restauranteId);
    Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

    restaurante.adicionarResponsavel(usuario);
  }



}
