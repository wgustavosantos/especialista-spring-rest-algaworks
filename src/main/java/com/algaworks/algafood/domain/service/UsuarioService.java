package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.v1.model.inputDto.UsuarioInputSenhaDTO;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario); /*Antes de chamaro findbyEmail o SDJPA faz o commit dos objetos gerenciados
        por isso é necessário desanexar do contexto de persistencia, pois vai adicionar no bd um usuario com o mesmo
        email*/

        final Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        /*Se um usuario vindo do banco com o mesmo email for diferente do usuario vindo da requisição, cai no if */
        if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new NegocioException(String.format(ErrorMessage.EMAIL_JA_CADASTRADO.get(), usuario.getEmail()));
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }


    @Transactional
    public Usuario atualizar(Usuario usuario) {

        return salvar(usuario);
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

    private Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    public Usuario buscar(Long id) {
        return buscarOuFalhar(id);

    }

    @Transactional
    public void alterarSenha(Long usuarioId, UsuarioInputSenhaDTO senhaDTO) {
        Usuario usuarioAtual = buscar(usuarioId);

        if(!passwordEncoder.matches(senhaDTO.getSenhaAtual(), usuarioAtual.getSenha()))
            throw new NegocioException(ErrorMessage.SENHA_NAO_COINCIDE.get());

        usuarioAtual.setSenha(senhaDTO.getNovaSenha());
    }

    public void adicionarGrupo(Long usuarioId, Long grupoId) {
        final Usuario usuario = buscarOuFalhar(usuarioId);
        final Grupo grupo = grupoService.buscar(grupoId);
        usuario.adicionarGrupo(grupo);
        salvar(usuario);
    }
    public void removerGrupo(Long usuarioId, Long grupoId) {
        final Usuario usuario = buscarOuFalhar(usuarioId);
        final Grupo grupo = grupoService.buscar(grupoId);
        usuario.removerGrupo(grupo);
        salvar(usuario);
    }

}
