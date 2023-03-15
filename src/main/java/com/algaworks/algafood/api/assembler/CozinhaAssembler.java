package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CozinhaAssembler {

    @Autowired
    ModelMapper modelMapper;

    public CozinhaDTO toDTO(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> toListDTO(List<Cozinha> cozinhas){
        return cozinhas.stream().map(this::toDTO).toList();
    }
}
