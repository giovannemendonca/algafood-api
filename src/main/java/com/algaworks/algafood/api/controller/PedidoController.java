package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoDTOAssembler;
import com.algaworks.algafood.api.assembler.PedidoInputDTODisassembler;
import com.algaworks.algafood.api.assembler.PedidoResumoDTOAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.dto.input.PedidoInputDTO;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private EmissaoPedidoService emissaoPedidoService;
    private PedidoRepository pedidoRepository;
    private PedidoDTOAssembler pedidoDTOAssembler;
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
    private PedidoInputDTODisassembler pedidoInputDTODisassembler;
    private PagedResourcesAssembler<Pedido> pedidoPagedResourcesAssembler;

    PedidoController(EmissaoPedidoService emissaoPedidoService,
                     PedidoRepository pedidoRepository,
                     PedidoDTOAssembler pedidoDTOAssembler,
                     PedidoResumoDTOAssembler pedidoResumoDTOAssembler,
                     PedidoInputDTODisassembler pedidoInputDTODisassembler,
                     PagedResourcesAssembler<Pedido> pedidoPagedResourcesAssembler
    ) {
        this.emissaoPedidoService = emissaoPedidoService;
        this.pedidoRepository = pedidoRepository;
        this.pedidoDTOAssembler = pedidoDTOAssembler;
        this.pedidoResumoDTOAssembler = pedidoResumoDTOAssembler;
        this.pedidoInputDTODisassembler = pedidoInputDTODisassembler;
        this.pedidoPagedResourcesAssembler = pedidoPagedResourcesAssembler;
    }

    @GetMapping
    public PagedModel<PedidoResumoDTO> pesquisar(
            PedidoFilter filtro,
            @PageableDefault(size = 10) Pageable pageable) {

        Pageable pageableTraduzido = pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        PagedModel<PedidoResumoDTO> pedidoResumoDTOS = pedidoPagedResourcesAssembler.toModel(pedidosPage, pedidoResumoDTOAssembler);

        return pedidoResumoDTOS;
    }


    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        return pedidoDTOAssembler.toModel(emissaoPedidoService.buscarOuFalhar(codigoPedido));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInputDTO pedidoInputDTO) {
        try {
            Pedido pedido = pedidoInputDTODisassembler.toDomainObject(pedidoInputDTO);
            // TODO pegar usuário autenticado
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            return pedidoDTOAssembler.toModel(emissaoPedidoService.emitir(pedido));
        } catch (EntidadeEmUsoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    // esse método é usado para traduzir os campos de ordenação
    private Pageable traduzirPageable(Pageable pageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(pageable, mapeamento);
    }
}
