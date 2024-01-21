package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler {

  @Autowired
  private ModelMapper modelMapper;


    public FotoProdutoDTO toDTO(FotoProduto fotoProduto){
      return modelMapper.map(fotoProduto, FotoProdutoDTO.class);
    }


}
