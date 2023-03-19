package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.inputDto.EstadoInputDTO;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDissasembler {

    @Autowired
    ModelMapper modelMapper;

    public Estado toDomainModel(EstadoInputDTO estadoInputDTO) {
        return modelMapper.map(estadoInputDTO, Estado.class);
    }

    public void copyProperties(EstadoInputDTO estadoInputDTO, Estado estado) {
        modelMapper.map(estadoInputDTO, estado);
    }
}
