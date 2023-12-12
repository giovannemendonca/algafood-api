package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.assembler.RestauranteDTOAssembler;
import com.algaworks.algafood.api.model.dto.EnderecoDTO;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.dto.input.ItemPedidoInputDTO;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    modelMapper.createTypeMap(ItemPedidoInputDTO.class, ItemPedido.class)
            .addMappings(mapper -> mapper.skip(ItemPedido::setId));
    // skip() ignora o mapeamento de um campo
    // basicamente, o mapeamento de ItemPedidoInputDTO para ItemPedido é feito, exceto pelo campo id

    return modelMapper;

  }

}
