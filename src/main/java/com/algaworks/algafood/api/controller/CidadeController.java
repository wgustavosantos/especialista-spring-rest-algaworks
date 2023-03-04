package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Cidade cidade) {
        try {
            Cidade c = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(c);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        final List<Cidade> cidades = cidadeService.listar();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return cidadeService.buscar(cidadeId);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@RequestBody @Valid Cidade cidade, @PathVariable Long id) {

        try {
            Estado estado = estadoService.buscar(cidade.getEstado().getId());
            cidade.setEstado(estado);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

        return cidadeService.atualizar(cidade, id);
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId) {
        cidadeService.deletar(cidadeId);

    }
}
