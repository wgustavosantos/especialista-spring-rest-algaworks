package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.ProdutoAssembler;
import com.algaworks.algafood.api.v1.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.ProdutoInputDTO;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoAssembler pAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PostMapping
    public ProdutoDTO adicionar(@RequestBody @Valid ProdutoInputDTO produtoInputDTO, @PathVariable Long restauranteId){
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        Produto produto = pAssembler.toDomainModel(produtoInputDTO);
        produto.setRestaurante(restaurante);

        return pAssembler.toModel(produtoService.salvar(produto));
    }

//    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<ProdutoDTO> listar(@PathVariable Long restauranteId,
                                              @RequestParam(required = false) Boolean incluirInativos){
        final Restaurante restaurante = restauranteService.buscar(restauranteId);
        List<Produto> produtos = null;

        if(incluirInativos){
            produtos = produtoService.listarTodosPorRestaurante(restaurante);
        } else {
            produtos = produtoService.listarAtivosPorRestaurante(restaurante);
        }


        return pAssembler.toCollectionModel(produtos)
                .add(algaLinks.linkToProdutos(restauranteId));
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long produtoId, @PathVariable Long restauranteId){
        final Produto produto = produtoService.buscar(produtoId, restauranteId);
        return pAssembler.toModel(produto);

    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        final Produto produto = produtoService.buscar(produtoId, restauranteId);

        pAssembler.copyProperties(produtoInputDTO, produto);

        return pAssembler.toModel(produto);

    }

}
