package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CozinhaService cozinhaService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {

        Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);

    }

    @GetMapping
    public List<Restaurante> listar() {

        return restauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {

        return restauranteService.buscar(restauranteId);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {

        Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha);

        return restauranteService.atualizar(restaurante, id);

    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId) {
            restauranteService.deletar(restauranteId);
    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteService.buscar(id);

        merge(campos, restauranteAtual);

        return atualizar(restauranteAtual, id);
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

            ObjectMapper objectMapper = new ObjectMapper();
            final Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            System.out.println(nomePropriedade + " = " + valorPropriedade);

            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            final Object novoValorConvertido = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValorConvertido);
        });
    }
}
