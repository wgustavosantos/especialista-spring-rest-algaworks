package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.UsuarioController;
import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioInputDTO;
import com.algaworks.algafood.api.v1.model.inputDto.usuarioInputUpdateDTO;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAssembler  extends RepresentationModelAssemblerSupport<Usuario, UsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UsuarioAssembler() {
        super(UsuarioController.class, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO toModel (Usuario usuario){

        final UsuarioDTO usuarioDTO = createModelWithId(usuario.getId(), usuario);

        modelMapper.map(usuario, usuarioDTO);

//        usuarioDTO.add(linkTo(methodOn(UsuarioController.class).buscar(usuarioDTO.getId())).withSelfRel());
//
//        usuarioDTO.add(linkTo(UsuarioController.class).withRel("usuarios"));
//
//        usuarioDTO.add(linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioDTO.getId())).withRel("grupos-usuario"));

        usuarioDTO.add(algaLinks.linkToUsuarios("usuarios"));

        usuarioDTO.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));

        return usuarioDTO;
    }

    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToUsuarios());
    }

    public Usuario toDomainModel (UsuarioInputDTO usuarioInputDTO){
        return modelMapper.map(usuarioInputDTO, Usuario.class);
    }

    public void copyProperties (usuarioInputUpdateDTO usuarioInputDTO, Usuario usuario){
        modelMapper.map(usuarioInputDTO, usuario);
    }
}
