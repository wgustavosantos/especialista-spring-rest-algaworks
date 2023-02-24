package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeNaoEncontradaException extends ResponseStatusException {
    /**
     * status code 404 Not Found
     *
     * @param mensagem
     */
    public EntidadeNaoEncontradaException(String mensagem) {
        this(HttpStatus.NOT_FOUND, mensagem);
    }

    /**
     * Custom status code
     * @param status
     * @param reason
     */
    public EntidadeNaoEncontradaException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
