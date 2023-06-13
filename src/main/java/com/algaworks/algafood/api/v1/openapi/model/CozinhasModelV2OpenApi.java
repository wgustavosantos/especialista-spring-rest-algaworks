package com.algaworks.algafood.api.v1.openapi.model;

import com.algaworks.algafood.api.v2.model.CozinhaDTOV2;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@Setter
@Getter
public class CozinhasModelV2OpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelV2OpenApi page;

    @Data
    public class CozinhasEmbeddedModelOpenApi {

        private List<CozinhaDTOV2> cozinhas;

    }

}
