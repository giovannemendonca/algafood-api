package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoDTOAssembler;
import com.algaworks.algafood.api.assembler.EstadoInputDTODisassembler;
import com.algaworks.algafood.api.model.dto.EstadoDTO;
import com.algaworks.algafood.api.model.dto.input.EstadoInputDTO;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoDTOAssembler estadoDTOAssembler;

    @Autowired
    EstadoInputDTODisassembler estadoInputDTODisassembler;

    @GetMapping
    public CollectionModel<EstadoDTO> listar() {
        List<Estado> todosEstados = estadoRepository.findAll();
        return estadoDTOAssembler.toCollectionModel(todosEstados);
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {

        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
        return estadoDTOAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO adicionar(@Valid @RequestBody EstadoInputDTO estadoInputDTO) {

        try {
            Estado estado = estadoInputDTODisassembler.toDomainObject(estadoInputDTO);
            estado = cadastroEstado.salvar(estado);
            EstadoDTO estadoDTO = estadoDTOAssembler.toModel(estado);
            return estadoDTO;
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());

        }
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@PathVariable Long estadoId, @Valid @RequestBody EstadoInputDTO estadoInputDTO) {
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
        estadoInputDTODisassembler.copyToDomainObject(estadoInputDTO, estadoAtual);
        EstadoDTO estadoDTO = estadoDTOAssembler.toModel(estadoAtual);
        return estadoDTO;

    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }

}
