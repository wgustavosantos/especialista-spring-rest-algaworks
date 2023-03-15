package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.inputDto.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Restaurante toDomainModel(RestauranteInputDTO restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante){

        //evitar Caused by: org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 4
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteInputDTO, restaurante);
    }
}
