package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Cozinha;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public CozinhaNaoEncontradaException(String msg) {
        super(msg);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Cozinha.class.getSimpleName(), cozinhaId));
    }
}
