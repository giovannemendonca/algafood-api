package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public CozinhaDTO toModel(Cozinha cozinha) {
    return modelMapper.map(cozinha, CozinhaDTO.class);
  }

  public List<CozinhaDTO> toCollectionModel(List<Cozinha> cozinhas) {
    return cozinhas.stream()
            .map(cozinha -> toModel(cozinha))
            .collect(Collectors.toList());
  }
}
