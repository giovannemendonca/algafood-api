package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    private ModelMapper modelMapper;

    PedidoResumoDTOAssembler(ModelMapper modelMapper) {
        super(PedidoController.class, PedidoResumoDTO.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido) {

        PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoResumoDTO);

        pedidoResumoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoResumoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedidoResumoDTO.getRestaurante().getId())).withSelfRel());

        pedidoResumoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedidoResumoDTO.getCliente().getId())).withSelfRel());


        return pedidoResumoDTO;
    }


}
