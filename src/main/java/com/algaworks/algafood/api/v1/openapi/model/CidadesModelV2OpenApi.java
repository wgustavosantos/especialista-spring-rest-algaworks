package com.algaworks.algafood.api.v1.openapi.model;

import com.algaworks.algafood.api.v2.model.CidadeDTOV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesModel")
@Data
public class CidadesModelV2OpenApi {

    private CidadesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadesEmbeddedModelOpenApi {

        private List<CidadeDTOV2> cidades;

    }

}
