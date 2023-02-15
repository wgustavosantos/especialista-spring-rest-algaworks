package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {

        return restauranteRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Restaurante de código %d não pôde ser encontrada", id)));
    }

    public Restaurante atualizar(Restaurante restaurante, Long id) {

        Restaurante r = this.buscar(id);

        BeanUtils.copyProperties(restaurante, r, "id");

        return restauranteRepository.save(r);
    }

    public void deletar(Long restauranteId) {

        try{
            restauranteRepository.deleteById(restauranteId);
        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Restaurante de código %d não pôde ser excluído, pois está em uso",
                    restauranteId));
        } catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Restaurante de código %d não pôde ser encontrado", restauranteId));
        }
    }
}
