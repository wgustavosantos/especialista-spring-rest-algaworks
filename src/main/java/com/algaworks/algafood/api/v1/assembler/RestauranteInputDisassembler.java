package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.inputDto.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Restaurante DTOtoDomainModel(RestauranteInputDTO restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyProperties(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante){

        //evitar Caused by: org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 4
        restaurante.setCozinha(new Cozinha());
        restaurante.getEndereco().setCidade(new Cidade());
        modelMapper.map(restauranteInputDTO, restaurante);
    }
}
