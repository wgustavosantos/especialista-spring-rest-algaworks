package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listar(){
        return cozinhaRepository.todas();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
        if(cozinha != null)
            return ResponseEntity.ok(cozinha);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Cozinha cozinha){
        cozinhaRepository.adicionar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long cozinhaId){
        Cozinha target = cozinhaRepository.porId(cozinhaId);

        if(target != null){
            BeanUtils.copyProperties(cozinha, target, "id");
            target = cozinhaRepository.adicionar(target);
            return ResponseEntity.ok(target);
        }
        return ResponseEntity.notFound().build();
    }
}
