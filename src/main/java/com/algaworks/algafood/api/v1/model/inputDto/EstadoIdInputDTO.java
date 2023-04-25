package com.algaworks.algafood.api.v1.model.inputDto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInputDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private Long id;
}
