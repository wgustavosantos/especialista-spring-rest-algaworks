package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Estado;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public EstadoNaoEncontradoException(String msg) {
        super(msg);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format(ErrorMessage.ENTIDADE_NOT_FOUND.get(), Estado.class.getSimpleName(), estadoId));
    }
}
