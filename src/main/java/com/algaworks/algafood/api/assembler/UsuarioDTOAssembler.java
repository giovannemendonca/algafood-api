package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.model.dto.UsuarioDTO;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

    private ModelMapper modelMapper;

    public UsuarioDTOAssembler(ModelMapper modelMapper) {
        super(UsuarioController.class, UsuarioDTO.class);
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioDTO toModel(Usuario usuario) {

        UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioDTO);

        usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
                .listar()).withRel("usuarios"));

        usuarioDTO.add(linkTo(methodOn(UsuarioGrupoController.class)
                .listar(usuarioDTO.getId())).withRel("grupos-usuario"));

        return usuarioDTO;

    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
    }
}
