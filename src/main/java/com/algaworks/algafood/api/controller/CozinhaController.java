package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
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

    @Autowired
    private CozinhaService cozinhaService;

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        cozinha = cozinhaService.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaService.buscar(cozinhaId);
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@RequestBody Cozinha cozinha, @PathVariable Long cozinhaId) {
        return cozinhaService.atualizar(cozinha, cozinhaId);
    }

    //    @DeleteMapping("/{cozinhaId}")
//    public ResponseEntity<Cozinha> deletar (@PathVariable Long cozinhaId) {
//        try {
//            cozinhaService.deletar(cozinhaId);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cozinhaService.deletar(cozinhaId);
    }
}
