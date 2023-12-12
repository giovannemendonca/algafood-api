package com.algaworks.algafood.api.model.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoInputDTO {

    @NotNull
    @Valid
    private RestauranteIdInputDTO restaurante;

    @NotNull
    @Valid
    private FormaPagamentoIdInputDTO formaPagamento;

    @NotNull
    @Valid
    private EnderecoInputDTO enderecoEntrega;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<ItemPedidoInputDTO> itens;
}
