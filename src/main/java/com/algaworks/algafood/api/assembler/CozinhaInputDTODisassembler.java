package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.model.dto.input.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDTODisassembler {


  @Autowired
  private ModelMapper modelMapper;

  public Cozinha toDomainObject(CozinhaInputDTO cozinhaInputDTO) {
    return modelMapper.map(cozinhaInputDTO, Cozinha.class);
  }

  public void copyToDomainObject(CozinhaInputDTO cozinhaInputDTO, Cozinha cozinha) {
    modelMapper.map(cozinhaInputDTO, cozinha);
  }
}
