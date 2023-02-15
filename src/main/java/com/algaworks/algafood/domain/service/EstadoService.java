package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long id) {
        return estadoRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Estado de código %d não pôde ser encontrada", id)));

    }

    public Estado atualizar(Estado estado, Long estadoId) {

        Estado estadoOriginal = buscar(estadoId);
        BeanUtils.copyProperties(estado, estadoOriginal, "id");

        return estadoRepository.save(estadoOriginal);
    }

    public void deletar(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pôde ser excluído, pois está em uso", estadoId));

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Estado de código %d não pôde ser encontrado", estadoId));
        }
    }






}
