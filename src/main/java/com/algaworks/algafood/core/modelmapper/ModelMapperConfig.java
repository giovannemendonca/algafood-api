package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.dto.EnderecoDTO;
import com.algaworks.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    var enderecoToEnderecoDTOTypeMap = modelMapper.createTypeMap(
        Endereco.class, EnderecoDTO.class);

    enderecoToEnderecoDTOTypeMap.<String>addMapping(
            src -> src.getCidade().getEstado().getNome(),
            (dest, value) -> dest.getCidade().setEstado(value)
    );

    return modelMapper;

  }

}
