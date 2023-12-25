package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificarClientePedidoConfirmadoListener {

    private final EnvioEmailService envioEmail;

    NotificarClientePedidoConfirmadoListener(EnvioEmailService envioEmail) {
        this.envioEmail = envioEmail;
    }

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

        Pedido pedido = event.getPedido();

        var mensagem = Mensagem.builder()
                .destinatario(pedido.getCliente().getEmail())
                .assunto(pedido.getRestaurante().getNome() + " - Pedido comfirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .build();

        envioEmail.enviar(mensagem);

    }

}
