package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import org.springframework.hateoas.CollectionModel;

public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoDTO> listar();

}