package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CozinhaInputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {

    PagedModel<CozinhaDTO> listar(Pageable pageable);

    CozinhaDTO buscar(Long cozinhaId);

    ResponseEntity<CozinhaDTO> adicionar(CozinhaInputDTO cozinhaInput);

    CozinhaDTO atualizar(CozinhaInputDTO cozinhaInput, Long cozinhaId);

    void deletar(Long cozinhaId);

}