package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    private ModelMapper modelMapper;

    public PedidoDTOAssembler(ModelMapper modelMapper) {
        super(PedidoController.class, PedidoDTO.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public PedidoDTO toModel(Pedido pedido) {

        PedidoDTO pedidoDTO = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoDTO);

        pedidoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedidoDTO.getRestaurante().getId())).withSelfRel());

        pedidoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedidoDTO.getCliente().getId())).withSelfRel());

        pedidoDTO.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedidoDTO.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoDTO.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedidoDTO.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoDTO.getItens().forEach(itemPedidoDTO -> itemPedidoDTO.add(linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProdutoId())).withRel("produto")));

        return pedidoDTO;
    }

}
