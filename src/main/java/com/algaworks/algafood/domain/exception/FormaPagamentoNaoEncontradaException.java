package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends RuntimeException {

  public FormaPagamentoNaoEncontradaException(String mensagem){
    super(mensagem);
  }

  public FormaPagamentoNaoEncontradaException(Long formaPagamentoId){
    this(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
  }

}
