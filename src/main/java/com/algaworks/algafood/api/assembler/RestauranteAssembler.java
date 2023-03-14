package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteAssembler {

    @Autowired
    ModelMapper modelMapper;
    public RestauranteDTO toDTO(Restaurante restaurante) {

       return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toListDTO(List<Restaurante> list){
        return list.stream().map(this::toDTO).toList();
    }
}
