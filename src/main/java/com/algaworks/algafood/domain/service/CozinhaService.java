package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    public Cozinha buscar(Long id){
        return cozinhaRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Cozinha de código %d não pôde ser encontrada", id)));
    }

    public Cozinha atualizar(Cozinha cozinha, Long id){
        final Cozinha c = buscar(id);
        BeanUtils.copyProperties(cozinha, c, "id");
        return cozinhaRepository.save(c);
    }

    public void deletar (Long cozinhaId){
        try {
            cozinhaRepository.deleteById(cozinhaId);
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pôde ser excluída, pois está em uso",
                    cozinhaId));
        }
        catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(HttpStatus.NOT_FOUND, String.format("Cozinha de código %d não pôde ser encontrada",
                    cozinhaId));
        }
    }
}
