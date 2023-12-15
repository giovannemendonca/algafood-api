package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoDTOAssembler;
import com.algaworks.algafood.api.assembler.PedidoInputDTODisassembler;
import com.algaworks.algafood.api.assembler.PedidoResumoDTOAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.model.dto.input.PedidoInputDTO;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public Page<PedidoResumoDTO> pesquisar(
            PedidoFilter filtro,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        List<PedidoResumoDTO> pedidoResumoDTO = pedidoResumoDTOAssembler.toCollectionDTO(pedidosPage.getContent());
        Page<PedidoResumoDTO> pedidoResumoDTOPage = new PageImpl<>(pedidoResumoDTO, pageable, pedidosPage.getTotalElements());

        return pedidoResumoDTOPage;

    }

    @GetMapping("/{codigoPedido}")
    public PedidoDTO buscar(@PathVariable String codigoPedido) {
        return pedidoDTOAssembler.toDTO(emissaoPedidoService.buscarOuFalhar(codigoPedido));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
