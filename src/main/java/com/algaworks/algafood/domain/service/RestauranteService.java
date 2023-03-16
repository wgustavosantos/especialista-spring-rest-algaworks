package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscar(cozinhaId);
        restaurante.setCozinha(cozinha);

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

    private Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
