package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.ProdutoInputDTO;
import org.springframework.hateoas.CollectionModel;

public interface RestauranteProdutoControllerOpenApi {


    CollectionModel<ProdutoDTO> listar(Long restauranteId, Boolean incluirInativos);

    ProdutoDTO buscar(Long restauranteId, Long produtoId);

    ProdutoDTO adicionar(ProdutoInputDTO produtoInput, Long restauranteId);

    ProdutoDTO atualizar(Long restauranteId, Long produtoId, ProdutoInputDTO produtoInput);
}