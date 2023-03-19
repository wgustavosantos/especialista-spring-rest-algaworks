package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.GrupoDTO;
import com.algaworks.algafood.api.model.dto.inputDto.GrupoInputDTO;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrupoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDTO toDTO (Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> toListDTO (List<Grupo> grupos){
        return grupos.stream().map(this::toDTO).toList();
    }

    public Grupo toDomainModel (GrupoInputDTO grupoInputDTO){
        return modelMapper.map(grupoInputDTO, Grupo.class);
    }

    public void copyProperties (GrupoInputDTO grupoInputDTO, Grupo grupo){
        modelMapper.map(grupoInputDTO, grupo);
    }
}
