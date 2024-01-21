package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDTOAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento){

    return new ModelMapper().map(formaPagamento, FormaPagamentoDTO.class);
  }


  public List<FormaPagamentoDTO> toCollectionDTO(Collection<FormaPagamento> formaPagamentos){
    return formaPagamentos.stream()
            .map(formaPagamento -> toDTO(formaPagamento))
            .collect(Collectors.toList());
  }



}
