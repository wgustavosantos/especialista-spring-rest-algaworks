package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteAssembler;
import com.algaworks.algafood.api.v1.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {
    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteAssembler rAssembler;

    @Autowired
    private FormaPagamentoAssembler fPAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        final Restaurante restaurante = restauranteService.buscar(restauranteId);

        final CollectionModel<FormaPagamentoDTO> formasPagamentoDTO = fPAssembler.toCollectionModel(restaurante.getFormasPagamento())
                .removeLinks()
                .add(algaLinks.linkToRestauranteFormasPagamento(restauranteId))
                .add(algaLinks.linkToRestauranteFormasPagamentoAssociacao(restauranteId, "associar"));

        formasPagamentoDTO.getContent().forEach(formaPagamentoDTO -> {
            formaPagamentoDTO.add(algaLinks.linkToRestauranteFormasPagamentoDesassociacao(restauranteId, formaPagamentoDTO.getId(), "desassociar"));
        });

        return formasPagamentoDTO;
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long formaPagamentoId, @PathVariable Long restauranteId){
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }


}
