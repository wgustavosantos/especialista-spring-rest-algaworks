package com.algaworks.algafood.api.openapi.model;

import com.algaworks.algafood.api.model.dto.CidadeDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesDTO")
@Data
public class CidadesModelOpenApi {

    private CidadeEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadeEmbeddedModelOpenApi {

        private List<CidadeDTO> cidades;

    }

}