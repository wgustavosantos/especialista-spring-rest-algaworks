package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.model.dto.inputDto.usuarioInputSenhaDTO;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }


    @Transactional
    public Usuario atualizar(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletar(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(Estado.class.getSimpleName(), usuarioId);

        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    public Usuario buscar(Long id) {
        return buscarOuFalhar(id);

    }

    @Transactional
    public void alterarSenha(Long usuarioId, usuarioInputSenhaDTO senhaDTO) {
        Usuario usuarioAtual = buscar(usuarioId);

        if(!usuarioAtual.senhaIsEquals(senhaDTO.getSenhaAtual()))
            throw new NegocioException(ErrorMessage.SENHA_NAO_COINCIDE.get());

        usuarioAtual.setSenha(senhaDTO.getNovaSenha());
    }
}
