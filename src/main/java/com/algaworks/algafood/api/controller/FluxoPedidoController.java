package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.FluxoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    private FluxoPedidoService fluxoPedido;

    FluxoPedidoController(FluxoPedidoService fluxoPedidoService) {
        this.fluxoPedido = fluxoPedidoService;
    }

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigoPedido){
        fluxoPedido.confirmar(codigoPedido);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String codigoPedido){
        fluxoPedido.entregar(codigoPedido);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String codigoPedido) {
        fluxoPedido.cancelar(codigoPedido);
    }

}
