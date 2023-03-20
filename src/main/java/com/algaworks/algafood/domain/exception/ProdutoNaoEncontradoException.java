package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Produto;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    /**
     * Mensagem do erro
     *
     * @param msg
     */
    public ProdutoNaoEncontradoException(String msg) {
        super(msg);
    }

    public ProdutoNaoEncontradoException(Long id) {
        super(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Produto.class.getSimpleName(), id));
    }
}
