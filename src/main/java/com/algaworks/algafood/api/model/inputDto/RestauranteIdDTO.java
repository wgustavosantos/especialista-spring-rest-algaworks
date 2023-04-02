package com.algaworks.algafood.api.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteIdDTO {

    @NotNull
    private Long id;
}
