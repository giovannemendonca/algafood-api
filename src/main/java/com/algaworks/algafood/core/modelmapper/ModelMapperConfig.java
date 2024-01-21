package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.v1.model.dto.EnderecoDTO;
import com.algaworks.algafood.api.v1.model.dto.input.ItemPedidoInputDTO;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class)
                .<String>addMapping(
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
