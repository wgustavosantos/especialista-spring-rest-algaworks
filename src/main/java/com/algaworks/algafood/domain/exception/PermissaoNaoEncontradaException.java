package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    /**
     * Mensagem do erro
     *
     * @param msg
     */
    public PermissaoNaoEncontradaException(String msg) {
        super(msg);
    }

    public PermissaoNaoEncontradaException(Long id) {
        this(String.format(ErrorMessage.PERMISSAO_NAO_ENCONTRADA.get(), id));
    }
}
