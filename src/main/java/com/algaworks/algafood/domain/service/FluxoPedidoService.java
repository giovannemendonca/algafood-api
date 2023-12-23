package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;


@Service
public class FluxoPedidoService {

    private final EmissaoPedidoService emissaoPedidoService;
    final EnvioEmailService envioEmail;

    FluxoPedidoService(EmissaoPedidoService emissaoPedidoService, EnvioEmailService envioEmailService) {
        this.emissaoPedidoService = emissaoPedidoService;
        this.envioEmail = envioEmailService;
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();

        var mensagem = Mensagem.builder()
                .destinatario(pedido.getCliente().getEmail())
                .assunto(pedido.getRestaurante().getNome() + " - Pedido comfirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .build();

        envioEmail.enviar(mensagem);
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
