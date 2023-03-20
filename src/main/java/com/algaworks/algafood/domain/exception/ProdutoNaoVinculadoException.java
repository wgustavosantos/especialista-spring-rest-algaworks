package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;

public class ProdutoNaoVinculadoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoVinculadoException(String msg) {
        super(msg);
    }

    public ProdutoNaoVinculadoException(Long restauranteId, Long produtoId) {
        this(String.format(ErrorMessage.PRODUTO_NAO_VINCULADO.get(), produtoId, restauranteId));
    }
}
