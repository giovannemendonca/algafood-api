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
  ModelMapper modelMapper;

  public Restaurante ToDomainObject(RestauranteInputDTO restauranteInputDTO){

    return new ModelMapper().map(restauranteInputDTO, Restaurante.class);
  }

  // esse metodo não retorna nada, ele apenas copia os dados de um objeto para outro
  public void CopyToDomainObject(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante){

    // Para evitar org.hibernate.HibernateException: identifier of an instance of
    // com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
    restaurante.setCozinha(new Cozinha());

    modelMapper.map(restauranteInputDTO, restaurante);
  }
}
