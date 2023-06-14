package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v2.model.CozinhaDTOV2;
import com.algaworks.algafood.api.v2.model.input.CozinhaInputDTOV2;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerV2OpenApi {

    PagedModel<CozinhaDTOV2> listar(Pageable pageable);

    CozinhaDTOV2 buscar(Long cozinhaId);

    CozinhaDTOV2 adicionar(CozinhaInputDTOV2 cozinhaInput);

    CozinhaDTOV2 atualizar(Long cozinhaId, CozinhaInputDTOV2 cozinhaInput);

    void remover(Long cozinhaId);

}
