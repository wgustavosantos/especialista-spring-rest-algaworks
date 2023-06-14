package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoDTO> listar();

}