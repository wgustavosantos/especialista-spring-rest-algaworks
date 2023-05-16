package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioAssembler uAssembler;

    @Autowired
    private AlgaLinks algaLinks;
    
    @Autowired
    private AlgaSecurity algaSecurity;

    @Override
    @GetMapping
    public CollectionModel<UsuarioDTO> listarResponsaveis(@PathVariable Long restauranteId){
        final Restaurante restaurante = restauranteService.buscar(restauranteId);

        CollectionModel<UsuarioDTO> usuariosModel = uAssembler
                .toCollectionModel(restaurante.getResponsaveis())
                .removeLinks();

        usuariosModel.add(algaLinks.linkToResponsaveisRestaurante(restauranteId));

        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            usuariosModel.add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

            usuariosModel.getContent().forEach(usuarioModel -> {
                usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
                        restauranteId, usuarioModel.getId(), "desassociar"));
            });
        }


        return usuariosModel;
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        restauranteService.associarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

}
