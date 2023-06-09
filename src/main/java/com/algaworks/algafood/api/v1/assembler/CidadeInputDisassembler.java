package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.inputDto.CidadeInputDTO;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Cidade DTOtoDomainModel(CidadeInputDTO cidadeInputDTO){
        return modelMapper.map(cidadeInputDTO, Cidade.class);
    }

    public void copyProperties(CidadeInputDTO cidadeInputDTO, Cidade cidade){
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInputDTO, cidade);
    }
}
