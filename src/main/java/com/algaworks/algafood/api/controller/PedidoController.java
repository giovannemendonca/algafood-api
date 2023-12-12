package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoDTOAssembler;
import com.algaworks.algafood.api.assembler.PedidoInputDTODisassembler;
import com.algaworks.algafood.api.assembler.PedidoResumoDTOAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.dto.input.PedidoInputDTO;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private EmissaoPedidoService emissaoPedidoService;
    private PedidoRepository pedidoRepository;
    private PedidoDTOAssembler pedidoDTOAssembler;
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

    private PedidoInputDTODisassembler pedidoInputDTODisassembler;

    PedidoController(EmissaoPedidoService emissaoPedidoService,
                     PedidoRepository pedidoRepository,
                     PedidoDTOAssembler pedidoDTOAssembler,
                     PedidoResumoDTOAssembler pedidoResumoDTOAssembler,
                     PedidoInputDTODisassembler pedidoInputDTODisassembler
    ) {
        this.emissaoPedidoService = emissaoPedidoService;
        this.pedidoRepository = pedidoRepository;
        this.pedidoDTOAssembler = pedidoDTOAssembler;
        this.pedidoResumoDTOAssembler = pedidoResumoDTOAssembler;
        this.pedidoInputDTODisassembler = pedidoInputDTODisassembler;
    }


    @GetMapping
    public List<PedidoResumoDTO> listar() {
        return pedidoResumoDTOAssembler.toCollectionDTO(pedidoRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        return pedidoDTOAssembler.toDTO(emissaoPedidoService.buscarOuFalhar(pedidoId));
    }

    @PostMapping
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInputDTO pedidoInputDTO) {
        try {
            Pedido pedido = pedidoInputDTODisassembler.toDomainObject(pedidoInputDTO);
            // TODO pegar usuário autenticado
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            return pedidoDTOAssembler.toDTO(emissaoPedidoService.emitir(pedido));
        } catch (EntidadeEmUsoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


}
