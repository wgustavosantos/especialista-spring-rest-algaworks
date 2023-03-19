package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    public List<Grupo> listar(){
        return grupoRepository.findAll();
    }

    public Grupo buscar(Long grupoId){
        return buscarOuFalhar(grupoId);
    }

    public Grupo atualizar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    public void deletar(Long grupoId){
        try {
            grupoRepository.deleteById(grupoId);
        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(Grupo.class.getSimpleName(), grupoId);
        } catch (EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(grupoId);
        }
    }

    private Grupo buscarOuFalhar(Long grupoId){
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
