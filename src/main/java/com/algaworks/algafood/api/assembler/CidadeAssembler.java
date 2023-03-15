package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.CidadeDTO;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadeAssembler {

    @Autowired
    ModelMapper modelMapper;

    public CidadeDTO toDTO(Cidade cidade){
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toListDTO(List<Cidade> cidades){
        return cidades.stream().map(this::toDTO).toList();
    }
}
