package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.CidadeDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CidadeInputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

    ResponseEntity<CidadeDTO> adicionar(CidadeInputDTO cidadeInputDTO);

    CollectionModel<CidadeDTO> listar();

    CidadeDTO buscar(Long cidadeId);


    CidadeDTO atualizar(CidadeInputDTO cidadeInputDTO, Long cidadeId);

    void deletar(Long cidadeId);
}
