package com.algaworks.algafood.api.v2.assembler;

import com.algaworks.algafood.api.v2.model.input.CozinhaInputDTOV2;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassemblerV2 {

    @Autowired
    ModelMapper modelMapper;

    public Cozinha DTOtoDomainModel(CozinhaInputDTOV2 cozinhaInputDTO){
        return modelMapper.map(cozinhaInputDTO, Cozinha.class);
    }

    public void copyProperties(CozinhaInputDTOV2 cozinhaInputDTO, Cozinha cozinha){
        modelMapper.map(cozinhaInputDTO, cozinha);
    }
}