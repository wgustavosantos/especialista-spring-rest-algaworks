package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.dto.CozinhaDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public CozinhaAssembler() {
        super(CozinhaController.class, CozinhaDTO.class);
    }

    @Override
    public CozinhaDTO toModel(Cozinha cozinha){
        final CozinhaDTO cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDTO);

        if (algaSecurity.podeConsultarCozinhas()) {
            cozinhaDTO.add(algaLinks.linkToCozinhas("cozinhas"));
        }

        return cozinhaDTO;
    }
}
