package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long id) {
        return buscarOuFalhar(id);

    }

    @Transactional
    public Estado atualizar(Estado estado) {

        return estadoRepository.save(estado);
    }

    @Transactional
    public void deletar(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(Estado.class.getSimpleName(), estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        }
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }
}
