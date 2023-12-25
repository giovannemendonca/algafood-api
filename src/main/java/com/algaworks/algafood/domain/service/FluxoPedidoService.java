package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class FluxoPedidoService {

    private final EmissaoPedidoService emissaoPedidoService;
    private final PedidoRepository pedidoRepository;

    FluxoPedidoService(EmissaoPedidoService emissaoPedidoService, PedidoRepository pedidoRepository) {
        this.emissaoPedidoService = emissaoPedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
        pedidoRepository.save(pedido);
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
        pedidoRepository.save(pedido);

    }


}
