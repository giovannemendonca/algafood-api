package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.input.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDTODisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public FormaPagamento toDomainObject(FormaPagamentoInputDTO formaPagamentoInputDTO){
    return new ModelMapper().map(formaPagamentoInputDTO, FormaPagamento.class);
  }

  public void copyToDomainObject(FormaPagamentoInputDTO formaPagamentoInputDTO, FormaPagamento formaPagamento){
    modelMapper.map(formaPagamentoInputDTO, formaPagamento);
  }

}
