package com.algaworks.algafood.domain.model;


import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO, CONFIRMADO);

    private String descricao;
    private List<StatusPedido> estadoAnteriores;

    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.estadoAnteriores = Arrays.asList(statusAnteriores);
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus){
        return !novoStatus.estadoAnteriores.contains(this);
    }
    public String getDescricao() {
        return this.descricao;
    }

}
