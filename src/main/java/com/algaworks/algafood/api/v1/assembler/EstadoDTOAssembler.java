package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.controller.EstadoController;
import com.algaworks.algafood.api.v1.links.AlgaLinks;
import com.algaworks.algafood.api.v1.model.dto.EstadoDTO;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    private final ModelMapper modelMapper;
    private final AlgaLinks algaLinks;

    EstadoDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(EstadoController.class, EstadoDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public EstadoDTO toModel(Estado estado) {
        EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoDTO);

        estadoDTO.add(algaLinks.linkToEstados("estados"));

        return estadoDTO;
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities).add(linkTo(EstadoController.class).withSelfRel());
    }
}
