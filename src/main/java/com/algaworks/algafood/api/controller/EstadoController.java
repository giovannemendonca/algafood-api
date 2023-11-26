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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<EstadoDTO> listar() {
    List<Estado> todosEstados = estadoRepository.findAll();
    List<EstadoDTO> estadosDTO = estadoDTOAssembler.toCollectionDTO(todosEstados);

    return estadosDTO;
  }

  @GetMapping("/{estadoId}")
  public EstadoDTO buscar(@PathVariable Long estadoId) {

    Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
    EstadoDTO estadoDTO = estadoDTOAssembler.toDTO(estado);

    return estadoDTO;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EstadoDTO adicionar(@Valid @RequestBody EstadoInputDTO estadoInputDTO) {

    try {
      Estado estado = estadoInputDTODisassembler.toDomainObject(estadoInputDTO);
      estado = cadastroEstado.salvar(estado);
      EstadoDTO estadoDTO = estadoDTOAssembler.toDTO(estado);
      return estadoDTO;
    } catch (Exception e) {
      throw new NegocioException(e.getMessage());

    }
  }

  @PutMapping("/{estadoId}")
  public EstadoDTO atualizar(@PathVariable Long estadoId, @Valid @RequestBody EstadoInputDTO estadoInputDTO) {
    Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
   estadoInputDTODisassembler.copyToDomainObject(estadoInputDTO, estadoAtual);
    EstadoDTO estadoDTO = estadoDTOAssembler.toDTO(estadoAtual);
    return estadoDTO;

  }

  @DeleteMapping("/{estadoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long estadoId) {
    cadastroEstado.excluir(estadoId);
  }

}
