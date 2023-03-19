package com.algaworks.algafood.api.model.dto.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeRefDTO {
    @NotNull
    private Long id;
}
