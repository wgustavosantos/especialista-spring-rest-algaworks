package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;

public class EntidadeEmUsoException extends NegocioException{

    public EntidadeEmUsoException(String entidade, Long id) {
        super(String.format(ErrorMessage.ENTIDADE_EM_USO.get(), entidade, id));
    }
}
