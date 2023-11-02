package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

  DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
  ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
  MESSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
  ERROR_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");


  private String title;
  private String uri;

  ProblemType(String path, String title) {
    this.uri = "https://algafood.com.br" + path;
    this.title = title;
  }

}

