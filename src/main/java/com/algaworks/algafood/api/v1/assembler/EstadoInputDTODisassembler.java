package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.input.EstadoInputDTO;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDTODisassembler {

  @Autowired
  private ModelMapper modelMapper;


  public Estado toDomainObject(EstadoInputDTO estadoInputDTO) {
    return modelMapper.map(estadoInputDTO, Estado.class);
  }

  public void copyToDomainObject(EstadoInputDTO estadoInputDTO, Estado estado) {
    modelMapper.map(estadoInputDTO, estado);
  }

}
