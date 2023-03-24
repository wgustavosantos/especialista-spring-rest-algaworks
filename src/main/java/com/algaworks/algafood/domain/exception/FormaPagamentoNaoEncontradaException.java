package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.FormaPagamento;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
    /**
     * Mensagem do erro
     *
     * @param msg
     */
    public FormaPagamentoNaoEncontradaException(String msg) {
        super(msg);
    }

    public FormaPagamentoNaoEncontradaException(Long id) {
        this(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), FormaPagamento.class.getSimpleName(), id));
    }
}
