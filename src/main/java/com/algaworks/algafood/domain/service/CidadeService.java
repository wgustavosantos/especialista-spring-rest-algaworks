package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscar(estadoId);

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return buscarOuFalhar(id);

    }

    @Transactional
    public Cidade atualizar(Cidade cidade) {

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void deletar(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(Cidade.class.getSimpleName(), cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        }
    }

    private Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository
                .findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }

}
