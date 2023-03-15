package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    public Cozinha buscar(Long id){
        return buscarOuFalhar(id);
    }

    @Transactional
    public Cozinha atualizar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void deletar (Long cozinhaId){
        try {
            cozinhaRepository.deleteById(cozinhaId);
            cozinhaRepository.flush();
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(Cozinha.class.getSimpleName(), cozinhaId);
        }
        catch(EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(cozinhaId);
        }
    }

    private Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository
                .findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }
}
