package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.ProdutoNaoVinculadoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Transactional
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    public List<Produto> listarAtivosPorRestaurante(Restaurante restauranteId){
        return produtoRepository.findByAtivosRestaurante(restauranteId);
    }

    public List<Produto> listarTodosPorRestaurante(Restaurante restauranteId){
        return produtoRepository.findByRestaurante(restauranteId);
    }

    public Produto buscar(Long produtoId, Long restauranteId){
        restauranteService.buscar(restauranteId);
        final Produto produto = buscarOuFalhar(produtoId);
        return produtoRepository.findByProdutoAndRestaurante(produto.getId(), restauranteId).
        orElseThrow(() -> new ProdutoNaoVinculadoException(restauranteId, produtoId));
    }

    @Transactional
    public Produto atualizar(Produto produto){
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deletar(Long produtoId){
        try {
            produtoRepository.deleteById(produtoId);
            produtoRepository.flush();
        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(Produto.class.getSimpleName(), produtoId);
        } catch (EmptyResultDataAccessException e){
            throw new ProdutoNaoEncontradoException(produtoId);
        }
    }

    private Produto buscarOuFalhar(Long produtoId){
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
