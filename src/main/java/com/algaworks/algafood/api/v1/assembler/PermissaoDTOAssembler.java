package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import com.algaworks.algafood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissaoDTOAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public PermissaoDTO toDTO(Permissao permissao) {
    return modelMapper.map(permissao, PermissaoDTO.class);
  }

  public List<PermissaoDTO> toCollectionDTO(Set<Permissao> permissaos) {
    return permissaos
            .stream()
            .map(permissao -> toDTO(permissao))
            .collect(Collectors.toList());
  }


}
