package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public RestauranteDTO toDTO(Restaurante restaurante) {
    return modelMapper.map(restaurante, RestauranteDTO.class);
  }

  public List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
    return restaurantes.stream()
            .map(restaurante -> toDTO(restaurante))
            .collect(Collectors.toList());
  }
}
