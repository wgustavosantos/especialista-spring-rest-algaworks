package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.EstadoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.EstadoInputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface EstadoControllerOpenApi {

    CollectionModel<EstadoDTO> listar();

    EstadoDTO buscar(Long estadoId);

    ResponseEntity<EstadoDTO> adicionar(EstadoInputDTO estadoInput);

    EstadoDTO atualizar(EstadoInputDTO  estadoInput, Long estadoId);

    void deletar(Long estadoId);
}
   