package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    /**
     * Mensagem do erro
     * @param msg
     */
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
