package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.input.PedidoInputDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDTODisassembler {

    private ModelMapper modelMapper;
    PedidoInputDTODisassembler(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Pedido toDomainObject(PedidoInputDTO pedidoInputDTO){
        return modelMapper.map(pedidoInputDTO, Pedido.class);
    }

    public void copyToDomainObject(PedidoInputDTO pedidoInputDTO, Pedido pedido){
        modelMapper.map(pedidoInputDTO, pedido);
    }

}
