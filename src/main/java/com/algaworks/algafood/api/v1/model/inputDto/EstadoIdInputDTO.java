package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInputDTO {

    @Schema(example = "1")
    @NotNull
    private Long id;
}
