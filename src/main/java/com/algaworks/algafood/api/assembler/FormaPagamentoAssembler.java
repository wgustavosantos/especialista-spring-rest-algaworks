package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.dto.inputDto.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FormaPagamentoAssembler {

    @Autowired
    ModelMapper modelMapper;

    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    public List<FormaPagamentoDTO> toListDTO(Collection<FormaPagamento> lista){
        return lista.stream().map(this::toDTO).toList();
    }

    public FormaPagamento DTOtoDomainObject (FormaPagamentoInputDTO formaPagamentoInputDTO){
        return modelMapper.map(formaPagamentoInputDTO, FormaPagamento.class);
    }

    public void copyProperties(FormaPagamentoInputDTO formaPagamentoInputDTO, FormaPagamento formaPagamento){
        modelMapper.map(formaPagamentoInputDTO, formaPagamento);
    }
}
