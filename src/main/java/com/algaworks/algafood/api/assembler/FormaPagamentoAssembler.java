package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.inputDto.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public FormaPagamentoAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoDTO.class);
    }

    @Override
    public FormaPagamentoDTO toModel(FormaPagamento formaPagamento){
        FormaPagamentoDTO formaPagamentoDTO =
                createModelWithId(formaPagamento.getId(), formaPagamento);

        modelMapper.map(formaPagamento, formaPagamentoDTO);

        formaPagamentoDTO.add(algaLinks.linkToFormasPagamento("formaPagamentos"));

        return formaPagamentoDTO;
    }

    @Override
    public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToFormasPagamento());
    }

    public FormaPagamento DTOtoDomainObject (FormaPagamentoInputDTO formaPagamentoInputDTO){
        return modelMapper.map(formaPagamentoInputDTO, FormaPagamento.class);
    }

    public void copyProperties(FormaPagamentoInputDTO formaPagamentoInputDTO, FormaPagamento formaPagamento){
        modelMapper.map(formaPagamentoInputDTO, formaPagamento);
    }
}
