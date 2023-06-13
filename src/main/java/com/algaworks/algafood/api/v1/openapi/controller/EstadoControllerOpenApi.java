package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.EstadoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.EstadoInputDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface EstadoControllerOpenApi {

    CollectionModel<EstadoDTO> listar();

    EstadoDTO buscar(Long estadoId);

    ResponseEntity<EstadoDTO> adicionar(EstadoInputDTO estadoInput);

    EstadoDTO atualizar(EstadoInputDTO  estadoInput, Long estadoId);

    void deletar(Long estadoId);
}
   