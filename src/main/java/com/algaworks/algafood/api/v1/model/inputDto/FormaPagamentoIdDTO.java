package com.algaworks.algafood.api.v1.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdDTO {

    @NotNull
    private Long id;
}