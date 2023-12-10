package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoResumoDTOAssembler {

  private ModelMapper modelMapper;

  PedidoResumoDTOAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public PedidoResumoDTO toDTO(Pedido pedido) {
    return modelMapper.map(pedido, PedidoResumoDTO.class);
  }

  public List<PedidoResumoDTO> toCollectionDTO(List<Pedido> pedidos) {
    return pedidos.stream()
            .map(this::toDTO)
            .toList();
  }
}
