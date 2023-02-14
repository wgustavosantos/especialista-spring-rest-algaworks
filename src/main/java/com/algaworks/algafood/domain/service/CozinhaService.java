package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void excluir (Long cozinhaId){
        try {
            cozinhaRepository.deleteById(cozinhaId);
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pôde ser excluída, pois está em uso",
                    cozinhaId));
        }
        catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não pôde ser encontrada",
                    cozinhaId));
        }
    }

    public Cozinha buscar(Long id){

        return cozinhaRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Cozinha de código %d não pôde ser encontrada", id)));
    }
}
