package com.algaworks.algafood.api.v1.controller;


import com.algaworks.algafood.api.v1.assembler.CidadeDTOAssembler;
import com.algaworks.algafood.api.v1.assembler.CidadeInputDTODisassembler;
import com.algaworks.algafood.api.v1.model.dto.CidadeDTO;
import com.algaworks.algafood.api.v1.model.dto.input.CidadeInputDTO;
import com.algaworks.algafood.api.utils.ResourceUriHelper;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;

    @Autowired
    private CidadeInputDTODisassembler cidadeInputDTODisassembler;

    @GetMapping
    public CollectionModel<CidadeDTO> listar() {

        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeDTOAssembler.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        return cidadeDTOAssembler.toModel(cidade);
    }

    @PostMapping
    public CidadeDTO adicionar(@Valid @RequestBody CidadeInputDTO cidadeInputDTO) {
        try {
            Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInputDTO);
            cidade = cadastroCidade.salvar(cidade);
            CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cidade);
            ResourceUriHelper.addUriInResponseHeader(cidadeDTO.getId());

            return cidadeDTO;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody CidadeInputDTO cidadeInputDTO) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
            cidadeInputDTODisassembler.copyToDomainObject(cidadeInputDTO, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);
            CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cidadeAtual);

            return cidadeDTO;
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }


}
