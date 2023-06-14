package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.CidadeDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CidadeInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

    @Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, " +
            "necessita de um estado e um nome válido")
    ResponseEntity<CidadeDTO> adicionar(CidadeInputDTO cidadeInputDTO);

    @Operation(summary = "Lista as cidades")
    CollectionModel<CidadeDTO> listar();

    @Operation(summary = "Busca uma cidade por Id")
    CidadeDTO buscar(Long cidadeId);

    @Operation(summary = "Atualizado uma cidade por ID")
    CidadeDTO atualizar(CidadeInputDTO cidadeInputDTO, Long cidadeId);

    @Operation(summary = "Excluir uma cidade por ID")
    void deletar(Long cidadeId);
}
