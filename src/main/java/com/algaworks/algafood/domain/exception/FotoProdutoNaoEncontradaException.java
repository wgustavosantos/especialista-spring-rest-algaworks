package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    /**
     * Mensagem do erro
     *
     * @param msg
     */
    public FotoProdutoNaoEncontradaException(String msg) {
        super(msg);
    }

    public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
        this(String.format(ErrorMessage.FOTO_PRODUTO_NAO_ENCONTRADO.get(),
                produtoId, restauranteId));
    }
}
