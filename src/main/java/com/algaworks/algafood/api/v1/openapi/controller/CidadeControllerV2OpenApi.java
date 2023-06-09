package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v2.model.CidadeDTOV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputDTOV2;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface CidadeControllerV2OpenApi {

    CollectionModel<CidadeDTOV2> listar();

    CidadeDTOV2 buscar(Long cidadeId);

    CidadeDTOV2 adicionar(CidadeInputDTOV2 cidadeInput);

    CidadeDTOV2 atualizar(Long cidadeId, CidadeInputDTOV2 cidadeInput);

    void remover(Long cidadeId);

}