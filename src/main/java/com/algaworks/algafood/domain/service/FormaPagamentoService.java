package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return formaPagamentoRepository.save(formaPagamento);

    }

    public List<FormaPagamento> listar(){
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento buscar(Long id) {
        return buscarOuFalhar(id);
    }

    @Transactional
    public FormaPagamento atualizar(FormaPagamento formaPagamento) {

        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void deletar(Long id){
        try{
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();

        } catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(FormaPagamento.class.getSimpleName(), id);
        } catch (EmptyResultDataAccessException e){
            throw new FormaPagamentoNaoEncontradaException(id);
        }
    }

    private FormaPagamento buscarOuFalhar(Long formaPagamentoId){
        return formaPagamentoRepository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }
}
