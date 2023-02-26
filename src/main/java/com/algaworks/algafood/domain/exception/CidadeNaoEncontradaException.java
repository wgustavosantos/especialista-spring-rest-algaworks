package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Cidade;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    /**
     * Mensagem customizada
     * @param msg
     */
    public CidadeNaoEncontradaException(String msg) {
        super(msg);
    }

    /**
     * Mensagem padrão
     * @param cidadeId
     */
    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Cidade.class.getSimpleName(), cidadeId));
    }
}
