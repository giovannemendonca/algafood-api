package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {


  @Autowired
  ModelMapper modelMapper;


  public static RestauranteDTO toDTO(Restaurante restaurante) {
   return new ModelMapper().map(restaurante, RestauranteDTO.class);
  }

  public static List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
    return restaurantes.stream().map(restaurante -> toDTO(restaurante)).collect(Collectors.toList());
  }
}
