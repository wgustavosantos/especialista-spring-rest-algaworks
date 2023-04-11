package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.ProdutoAssembler;
import com.algaworks.algafood.api.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.model.inputDto.ProdutoInputDTO;
import com.algaworks.algafood.api.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoAssembler pAssembler;

    @Override
    @PostMapping
    public ProdutoDTO adicionar(@RequestBody @Valid ProdutoInputDTO produtoInputDTO, @PathVariable Long restauranteId){
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        Produto produto = pAssembler.toDomainModel(produtoInputDTO);
        produto.setRestaurante(restaurante);

        return pAssembler.toDTO(produtoService.salvar(produto));
    }

    @Override
    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId,
                                   @RequestParam(required = false) boolean incluirInativos){
        final Restaurante restaurante = restauranteService.buscar(restauranteId);
        List<Produto> produtos = null;

        if(incluirInativos){
            produtos = produtoService.listarTodosPorRestaurante(restaurante);
        } else {
            produtos = produtoService.listarAtivosPorRestaurante(restaurante);
        }


        return pAssembler.toListDTO(produtos);
    }

    @Override
    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long produtoId, @PathVariable Long restauranteId){
        final Produto produto = produtoService.buscar(produtoId, restauranteId);
        return pAssembler.toDTO(produto);

    }

    @Override
    @PutMapping("/{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        final Produto produto = produtoService.buscar(produtoId, restauranteId);

        pAssembler.copyProperties(produtoInputDTO, produto);

        return pAssembler.toDTO(produto);

    }

}
