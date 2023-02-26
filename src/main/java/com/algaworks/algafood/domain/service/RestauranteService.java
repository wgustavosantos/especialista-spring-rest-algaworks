package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Cozinha;
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

    public Restaurante atualizar(Restaurante restaurante, Long id) {

        Restaurante r = this.buscar(id);

        BeanUtils.copyProperties(restaurante, r, "id",  "formasPagamento", "endereco", "dataCadastro", "produtos");

        return restauranteRepository.save(r);
    }
    public void deletar(Long restauranteId) {

        try{
            restauranteRepository.deleteById(restauranteId);
        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(ErrorMessage.ENTIDADE_EM_USO.get(), Restaurante.class.getSimpleName(),
                    restauranteId));
        } catch(EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException(restauranteId);
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
