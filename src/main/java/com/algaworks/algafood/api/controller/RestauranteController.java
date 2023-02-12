package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante r){
        Restaurante restauranteAdd = restauranteRepository.adicionar(r);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteAdd);
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar(){
        final List<Restaurante> todos = restauranteRepository.todos();

        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        final Restaurante restaurante = restauranteRepository.porId(restauranteId);

        if(restaurante == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(restaurante);
    }
}
