package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CozinhaAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

    @Autowired
    ModelMapper modelMapper;

    public CozinhaAssembler() {
        super(CozinhaController.class, CozinhaDTO.class);
    }

    @Override
    public CozinhaDTO toModel(Cozinha cozinha){
        final CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDTO);

        cozinhaDTO.add(linkTo(CidadeController.class).withRel("cozinhas"));

        return cozinhaDTO;
    }


}
