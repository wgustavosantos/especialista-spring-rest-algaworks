package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    /**
     * Mensagem do erro
     *
     * @param msg
     */
    public PedidoNaoEncontradoException(String msg) {
        super(msg);
    }
    public PedidoNaoEncontradoException(Long id) {
        super(String.format(ErrorMessage.PEDIDO_NAO_ENCONTRADO.get(), id));
    }

}
