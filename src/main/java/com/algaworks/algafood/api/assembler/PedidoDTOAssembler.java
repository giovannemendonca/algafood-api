package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoDTOAssembler {

  private ModelMapper modelMapper;

  PedidoDTOAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public PedidoDTO toDTO(Pedido pedido) {
    return modelMapper.map(pedido, PedidoDTO.class);
  }

  public List<PedidoDTO> toCollectionDTO(List<Pedido> pedidos) {
    return pedidos.stream()
            .map(this::toDTO)
            .toList();
  }
}
