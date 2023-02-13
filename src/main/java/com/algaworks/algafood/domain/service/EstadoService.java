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
        return estadoRepository.salvar(estado);
    }

    public void excluir(Long estadoId) {
        try {

            estadoRepository.excluir(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pôde ser excluído, pois está em uso",
                    estadoId));

        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não pôde ser encontrado",
                    estadoId));
        }
    }

    public Estado buscar(Long id) {
        final Estado estado = estadoRepository.buscar(id);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não pôde ser encontrada",
                    id));
        }
        return estado;
    }

    public List<Estado> listar() {
        return estadoRepository.todos();
    }

    public Estado atualizar(Estado estado, Long estadoId) {

        Estado estadoOriginal = buscar(estadoId);
        BeanUtils.copyProperties(estado, estadoOriginal, "id");

        return estadoRepository.salvar(estadoOriginal);
    }
}
