package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.model.dto.ProdutoDTO;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoDTOAssembler {

  @Autowired
  private ModelMapper modelMapper;


    public FotoProdutoDTO toDTO(FotoProduto fotoProduto){
      return modelMapper.map(fotoProduto, FotoProdutoDTO.class);
    }


}
