package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Restaurante;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException(String msg) {
        super(msg);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Restaurante.class.getSimpleName(), restauranteId));
    }
}
