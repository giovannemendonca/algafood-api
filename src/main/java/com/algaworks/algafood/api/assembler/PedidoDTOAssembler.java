package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.links.AlgaLinks;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    private ModelMapper modelMapper;
    private AlgaLinks algaLinks;

    public PedidoDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(PedidoController.class, PedidoDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public PedidoDTO toModel(Pedido pedido) {

        PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);

        modelMapper.map(pedido, pedidoDTO);

        pedidoDTO.add(algaLinks.linkToPedido());
        pedidoDTO.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
        pedidoDTO.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
        pedidoDTO.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));

        pedidoDTO.getRestaurante().add(
                algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoDTO.getCliente().add(
                algaLinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoDTO.getFormaPagamento().add(
                algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoDTO.getEnderecoEntrega().getCidade().add(
                algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoDTO.getItens().forEach(item -> {
            item.add(algaLinks.linkToProduto(
                    pedidoDTO.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });

        return pedidoDTO;
    }

}
