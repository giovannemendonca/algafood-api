package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.links.AlgaLinks;
import com.algaworks.algafood.api.v1.model.dto.CozinhaDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTOAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

    private ModelMapper modelMapper;
    private AlgaLinks algaLinks;

    public CozinhaDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(CozinhaController.class, CozinhaDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public CozinhaDTO toModel(Cozinha cozinha) {

        CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDTO);
        cozinhaDTO.add(algaLinks.linkToCozinhas("cozinhas"));

        return cozinhaDTO;
    }


}
