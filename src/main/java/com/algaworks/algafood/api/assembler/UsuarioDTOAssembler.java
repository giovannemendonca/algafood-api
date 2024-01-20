package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.links.AlgaLinks;
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
    private AlgaLinks algaLinks;

    public UsuarioDTOAssembler(ModelMapper modelMapper, AlgaLinks algaLinks) {
        super(UsuarioController.class, UsuarioDTO.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
    }

    @Override
    public UsuarioDTO toModel(Usuario usuario) {

        UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioDTO);

        usuarioDTO.add(algaLinks.linkToUsuarios("usuarios"));
        usuarioDTO.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));

        return usuarioDTO;

    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
    }
}
