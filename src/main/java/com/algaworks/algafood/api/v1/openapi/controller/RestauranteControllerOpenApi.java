package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.RestauranteApenasNomeDTO;
import com.algaworks.algafood.api.v1.model.dto.RestauranteBasicoDTO;
import com.algaworks.algafood.api.v1.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.v1.model.inputDto.RestauranteInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@SecurityRequirement(name = "security_auth")
public interface RestauranteControllerOpenApi {

    RestauranteDTO adicionar(RestauranteInputDTO restauranteInput);

//    @JsonView(RestauranteView.Resumo.class)
@Operation(parameters = {
        @Parameter(name = "projecao",
                description = "Nome da projeção",
                example = "apenas-nome",
                in = ParameterIn.QUERY,
                required = false
        )
})
    CollectionModel<RestauranteBasicoDTO> listar();

    @Operation(hidden = true)
    CollectionModel<RestauranteApenasNomeDTO> listarApenasNome();

    RestauranteDTO buscar(Long restauranteId);

    RestauranteDTO atualizar(RestauranteInputDTO restauranteInput, Long restauranteId);

    void deletar(Long restauranteId);


    ResponseEntity<Void> ativar(Long restauranteId);

    ResponseEntity<Void> inativar(Long restauranteId);

    ResponseEntity<Void> abrir(Long restauranteId);

    ResponseEntity<Void> fechar(Long restauranteId);

    void ativarRestaurantes(@RequestBody Set<Long> restauranteIds);

    @DeleteMapping("/inativacoes")
    void inativarRestaurantes(@RequestBody Set<Long> restauranteIds);

    @PatchMapping("/{id}")
    RestauranteDTO atualizarParcial(Long id, @RequestBody Map<String, Object> campos, HttpServletRequest servletRequest);
}
