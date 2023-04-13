package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.model.inputDto.UsuarioInputDTO;
import com.algaworks.algafood.api.model.inputDto.usuarioInputUpdateDTO;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioAssembler  extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioAssembler() {
        super(UsuarioController.class, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO toModel (Usuario usuario){

        final UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);

        modelMapper.map(usuario, usuarioDTO);

        usuarioDTO.add(linkTo(methodOn(UsuarioController.class).buscar(usuarioDTO.getId())).withSelfRel());

        usuarioDTO.add(linkTo(UsuarioController.class).withRel("usuarios"));

        usuarioDTO.add(linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioDTO.getId())).withRel("grupos-usuario"));

        return usuarioDTO;
    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(linkTo(UsuarioController.class).withSelfRel());
    }

    public Usuario toDomainModel (UsuarioInputDTO usuarioInputDTO){
        return modelMapper.map(usuarioInputDTO, Usuario.class);
    }

    public void copyProperties (usuarioInputUpdateDTO usuarioInputDTO, Usuario usuario){
        modelMapper.map(usuarioInputDTO, usuario);
    }
}
