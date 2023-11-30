package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.input.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDTODisassembler {
  @Autowired
  private ModelMapper modelMapper;

  public Restaurante toDomainObject(RestauranteInputDTO restauranteInput) {
    return modelMapper.map(restauranteInput, Restaurante.class);
  }

  public void copyToDomainObject(RestauranteInputDTO restauranteInput, Restaurante restaurante) {
    // Para evitar org.hibernate.HibernateException: identifier of an instance of
    // com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
    restaurante.setCozinha(new Cozinha());

    modelMapper.map(restauranteInput, restaurante);
  }
}
