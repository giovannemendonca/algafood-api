package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoDTOAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private EmissaoPedidoService emissaoPedidoService;
  private PedidoRepository pedidoRepository;
  private PedidoDTOAssembler pedidoDTOAssembler;

  PedidoController(EmissaoPedidoService emissaoPedidoService,
                   PedidoRepository pedidoRepository,
                   PedidoDTOAssembler pedidoDTOAssembler) {
    this.emissaoPedidoService = emissaoPedidoService;
    this.pedidoRepository = pedidoRepository;
    this.pedidoDTOAssembler = pedidoDTOAssembler;
  }


  @GetMapping
  public List<PedidoDTO> listar() {
    return pedidoDTOAssembler.toCollectionDTO(pedidoRepository.findAll());
  }

  @GetMapping("/{pedidoId}")
  public PedidoDTO buscar(@PathVariable Long pedidoId) {
    return pedidoDTOAssembler.toDTO(emissaoPedidoService.buscarOuFalhar(pedidoId));
  }


}
