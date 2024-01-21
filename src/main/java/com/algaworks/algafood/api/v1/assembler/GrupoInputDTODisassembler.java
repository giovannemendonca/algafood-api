package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.input.GrupoInputDTO;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDTODisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Grupo toDomainObject(GrupoInputDTO grupoInputDTO){
    return modelMapper.map(grupoInputDTO, Grupo.class);
  }

  public void copyToDomainObject(GrupoInputDTO grupoInputDTO, Grupo grupo){
    modelMapper.map(grupoInputDTO, grupo);
  }

}
