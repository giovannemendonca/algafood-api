package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class FluxoPedidoService {

    private EmissaoPedidoService emissaoPedidoService;

    FluxoPedidoService(EmissaoPedidoService emissaoPedidoService) {
        this.emissaoPedidoService = emissaoPedidoService;
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
    }


}
