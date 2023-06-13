package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.FormaPagamentoInputDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

public interface FormaPagamentoControllerOpenApi {

    ResponseEntity<FormaPagamentoDTO> adicionar(FormaPagamentoInputDTO formaPagamentoInputDTO);

    ResponseEntity<FormaPagamentoDTO> buscar(Long formaPagamentoId, ServletWebRequest request);

    ResponseEntity<FormaPagamentoDTO> atualizar(Long formaPagamentoId, FormaPagamentoInputDTO formaPagamentoInputDTO);

    ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

    ResponseEntity<Void> deletar(Long formaPagamentoId);
}
