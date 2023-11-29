package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoDTODisassembler;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.dto.input.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

  @Autowired
  CadastroFormaPagamentoService cadastroFormaPagamentoService;

  @Autowired
  FormaPagamentoRepository formaPagamentoRepository;

  @Autowired
  FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

  @Autowired
  FormaPagamentoDTODisassembler formaPagamentoDTODisassembler;

  @GetMapping
  public List<FormaPagamentoDTO> listar(){
    List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();

    return formaPagamentoDTOAssembler.toCollectionDTO(formaPagamentos);
  }

  @GetMapping("/{formaPagamentoId}")
  public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId){
    FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
    return formaPagamentoDTOAssembler.toDTO(formaPagamento);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FormaPagamentoDTO adicionar(@Valid @RequestBody FormaPagamentoInputDTO formaPagamentoInputDTO){
    FormaPagamento formaPagamento = formaPagamentoDTODisassembler.toDomainObject(formaPagamentoInputDTO);
    FormaPagamento  formaPagamentoSalva = cadastroFormaPagamentoService.salvar(formaPagamento);

    return formaPagamentoDTOAssembler.toDTO(formaPagamentoSalva);
  }

  @PutMapping("/{formaPagamentoId}")
  public FormaPagamentoDTO atualizar(@Valid @PathVariable Long formaPagamentoId, @RequestBody FormaPagamentoInputDTO formaPagamentoInputDTO){

    FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
    formaPagamentoDTODisassembler.copyToDomainObject(formaPagamentoInputDTO, formaPagamento);
    FormaPagamento formaPagamentoAtualizado = cadastroFormaPagamentoService.salvar(formaPagamento);

    return formaPagamentoDTOAssembler.toDTO(formaPagamentoAtualizado);
  }

  @DeleteMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long formaPagamentoId){
    cadastroFormaPagamentoService.excluir(formaPagamentoId);
  }



}
