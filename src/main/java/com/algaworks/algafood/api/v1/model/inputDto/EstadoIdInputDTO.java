package com.algaworks.algafood.api.v1.model.inputDto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInputDTO {

    @Schema(example = "1")
    @NotNull
    private Long id;
}
