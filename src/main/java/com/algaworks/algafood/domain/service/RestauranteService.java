package com.algaworks.algafood.domain.service;

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
        Cozinha cozinha = cozinhaService.buscar(cozinhaId);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante buscar(Long id) {

        return restauranteRepository.buscar(id);
    }

    public Restaurante atualizar(Restaurante restaurante, Long id) {
        Cozinha c = cozinhaService.buscar(restaurante.getCozinha().getId());

        Restaurante r = this.buscar(id);

        if(r == null )
           return null;
        restaurante.setCozinha(c);
        BeanUtils.copyProperties(restaurante, r, "id");

        return restauranteRepository.salvar(r);
    }
}
