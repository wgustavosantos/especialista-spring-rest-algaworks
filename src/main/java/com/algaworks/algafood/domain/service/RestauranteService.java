package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscar(cozinhaId);
        restaurante.setCozinha(cozinha);

        Cidade cidade = cidadeService.buscar(restaurante.getEndereco().getCidade().getId());
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return buscarOuFalhar(id);
    }

    @Transactional
    public Restaurante atualizar(Restaurante restaurante) {


        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void deletar(Long restauranteId) {

        try{
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(Restaurante.class.getSimpleName(), restauranteId);
        } catch(EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(restauranteId);
        }
    }

    @Transactional
    public void ativar(Long restauranteId){
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId){
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.inativar();
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        final FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);

    }
    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        final FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);

        restaurante.associarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrir(Long restauranteId){
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abrir();
        restauranteRepository.flush();
    }

    public void fechar(Long restauranteId) {
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.fechar();
        restauranteRepository.flush();
    }

    private Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }


    @Transactional
    public void associarResponsavel(Long restauranteId, Long usuarioId) {
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        final Usuario responsavel = usuarioService.buscar(usuarioId);
        restaurante.adicionarResponsavel(responsavel);
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
        final Restaurante restaurante = buscarOuFalhar(restauranteId);
        final Usuario responsavel = usuarioService.buscar(usuarioId);
        restaurante.removerResponsavel(responsavel);
    }

    @Transactional
    public void ativarEmMassa(Set<Long> restauranteIds){
        restauranteIds.forEach(this::ativar);
    }

    @Transactional
    public void inativarEmMassa(Set<Long> restauranteIds){
        restauranteIds.forEach(this::inativar);
    }
}
