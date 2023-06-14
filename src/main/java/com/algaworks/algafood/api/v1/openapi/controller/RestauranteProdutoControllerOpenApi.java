package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.ProdutoInputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoControllerOpenApi {


    CollectionModel<ProdutoDTO> listar(Long restauranteId, Boolean incluirInativos);

    ProdutoDTO buscar(Long restauranteId, Long produtoId);

    ProdutoDTO adicionar(ProdutoInputDTO produtoInput, Long restauranteId);

    ProdutoDTO atualizar(Long restauranteId, Long produtoId, ProdutoInputDTO produtoInput);
}