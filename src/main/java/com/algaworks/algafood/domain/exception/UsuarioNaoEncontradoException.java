package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Usuario;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {
    /**
     * Mensagem do erro
     *
     * @param msg
     */
    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }

    public UsuarioNaoEncontradoException(Long id) {
        super(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Usuario.class.getSimpleName(), id));
    }
}
