package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.model.dto.inputDto.UsuarioInputDTO;
import com.algaworks.algafood.api.model.dto.inputDto.usuarioInputUpdateDTO;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UsuarioAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toDTO (Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> toListDTO (Collection<Usuario> usuarios){
        return usuarios.stream().map(this::toDTO).toList();
    }

    public Usuario toDomainModel (UsuarioInputDTO usuarioInputDTO){
        return modelMapper.map(usuarioInputDTO, Usuario.class);
    }

    public void copyProperties (usuarioInputUpdateDTO usuarioInputDTO, Usuario usuario){
        modelMapper.map(usuarioInputDTO, usuario);
    }
}
