package com.algaworks.algafood.domain.listener;


import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificarClientePedidoCanceladoListener {

    private final EnvioEmailService envioEmail;

    NotificarClientePedidoCanceladoListener(EnvioEmailService envioEmail) {
        this.envioEmail = envioEmail;
    }

    @TransactionalEventListener
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();

        var mensagem = Mensagem.builder()
                .destinatario(pedido.getCliente().getEmail())
                .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
                .corpo("pedido-cancelado.html")
                .variavel("pedido", pedido)
                .build();

        envioEmail.enviar(mensagem);
    }
}
