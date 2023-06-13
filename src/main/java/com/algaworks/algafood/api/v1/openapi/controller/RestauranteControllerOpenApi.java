package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.dto.RestauranteApenasNomeDTO;
import com.algaworks.algafood.api.v1.model.dto.RestauranteBasicoDTO;
import com.algaworks.algafood.api.v1.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.v1.model.inputDto.RestauranteInputDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public interface RestauranteControllerOpenApi {

    RestauranteDTO adicionar(RestauranteInputDTO restauranteInput);

//    @JsonView(RestauranteView.Resumo.class)
    CollectionModel<RestauranteBasicoDTO> listar();

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
