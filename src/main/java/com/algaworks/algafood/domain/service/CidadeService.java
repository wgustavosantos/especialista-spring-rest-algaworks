package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscar(estadoId);

        cidade.setEstado(estado);
        return cidadeRepository.salvar(cidade);
    }

    public List<Cidade> listar(){
        return cidadeRepository.todas();
    }

    public Cidade atualizar(Cidade cidade, Long id) {
        final Estado estado = estadoService.buscar(cidade.getEstado().getId());

        Cidade c = this.buscar(id);

        if (c == null)
            return null;
        cidade.setEstado(estado);
        BeanUtils.copyProperties(cidade, c, "id");

        return cidadeRepository.salvar(c);
    }

    public void deletar(Long cidadeId) {
        final Cidade cidade = buscar(cidadeId);
        if(cidade == null){
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não pôde ser encontrada",
                    cidadeId));
        }
        cidadeRepository.remover(cidade);
    }

    public Cidade buscar(Long id) {
        final Cidade cidade = cidadeRepository.buscar(id);

        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade de código %d não pôde ser encontrada",
                    id));
        }
        return cidade;
    }
}
