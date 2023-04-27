package com.algaworks.algafood.api.v2.assembler;

import com.algaworks.algafood.api.v2.model.input.CidadeInputDTOV2;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassemblerV2 {

    @Autowired
    ModelMapper modelMapper;

    public Cidade DTOtoDomainModel(CidadeInputDTOV2 cidadeInputDTOV2){
        return modelMapper.map(cidadeInputDTOV2, Cidade.class);
    }

    public void copyProperties(CidadeInputDTOV2 cidadeInputDTOV2, Cidade cidade){
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInputDTOV2, cidade);
    }
}
