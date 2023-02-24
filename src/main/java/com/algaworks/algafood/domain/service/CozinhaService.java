package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return buscarOuFalhar(id);
    }


    public Cozinha atualizar(Cozinha cozinha, Long id){
        Cozinha cAtual = buscar(id);
        BeanUtils.copyProperties(cozinha, cAtual, "id");
        return cozinhaRepository.save(cAtual);
    }

    public void deletar (Long cozinhaId){
        try {
            cozinhaRepository.deleteById(cozinhaId);
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(ErrorMessage.ENTIDADE_EM_USO.get(), Cozinha.class.getSimpleName(),
                    cozinhaId));
        }
        catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Cozinha.class.getSimpleName(),
                    cozinhaId));
        }
    }

    private Cozinha buscarOuFalhar(Long id) {
        return cozinhaRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Cozinha.class.getSimpleName(), id)));
    }
}
