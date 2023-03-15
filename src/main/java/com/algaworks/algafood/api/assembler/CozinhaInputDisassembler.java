package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.inputDto.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Cozinha DTOtoDomainModel(CozinhaInputDTO cozinhaInputDTO){
        return modelMapper.map(cozinhaInputDTO, Cozinha.class);
    }

    public void copyProperties(CozinhaInputDTO cozinhaInputDTO, Cozinha cozinha){
        modelMapper.map(cozinhaInputDTO, cozinha);
    }
}