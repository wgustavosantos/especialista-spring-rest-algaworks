package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        cozinhaRepository.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long cozinhaId){
        Cozinha target = cozinhaRepository.porId(cozinhaId);

        if(target != null){
            BeanUtils.copyProperties(cozinha, target, "id");
            target = cozinhaService.salvar(target);
            return ResponseEntity.ok(target);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> deletar(@PathVariable Long cozinhaId){
        Cozinha target = cozinhaRepository.porId(cozinhaId);

        try {
            if(target != null){
                cozinhaRepository.remover(target);
                return ResponseEntity.noContent().build();
            }
        } catch(DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.notFound().build();
    }
}
