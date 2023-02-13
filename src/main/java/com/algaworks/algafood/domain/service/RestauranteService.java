package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.porId(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não pôde ser encontrada",
                    cozinhaId));
        }

        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante buscar(Long id) {

        return restauranteRepository.porId(id);
    }

    public Restaurante atualizar(Restaurante restaurante, Long id) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha c = cozinhaRepository.porId(cozinhaId);

        if (c == null)
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não pôde ser encontrada",
                    cozinhaId));

        Restaurante r = restauranteRepository.porId(id);

        if(r == null )
           return null;
        restaurante.setCozinha(c);
        BeanUtils.copyProperties(restaurante, r, "id");

        return restauranteRepository.salvar(r);
    }
}
