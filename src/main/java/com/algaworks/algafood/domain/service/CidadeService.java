package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long id) {
        return buscarOuFalhar(id);

    }

    public Cidade atualizar(Cidade cidade, Long id) {
        Estado estado = estadoService.buscar(cidade.getEstado().getId());
        cidade.setEstado(estado);

        Cidade c = this.buscar(id);
        BeanUtils.copyProperties(cidade, c, "id");
        return cidadeRepository.save(c);
    }

    public void deletar(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(ErrorMessage.ENTIDADE_EM_USO.get(), Cidade.class.getSimpleName(), cidadeId));

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Cidade.class.getSimpleName(), cidadeId));
        }
    }

    private Cidade buscarOuFalhar(Long id) {
        return cidadeRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Cidade.class.getSimpleName(), id)));
    }

}
