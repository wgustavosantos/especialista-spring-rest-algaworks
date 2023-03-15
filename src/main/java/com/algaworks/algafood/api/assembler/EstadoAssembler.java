package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.EstadoDTO;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoAssembler {

    @Autowired
    ModelMapper modelMapper;

    public EstadoDTO toDTO(Estado estado){
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toListDTO(List<Estado> estados){
        return estados.stream().map(this::toDTO).toList();
    }

}
