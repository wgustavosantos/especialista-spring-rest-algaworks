package com.algaworks.algafood.domain.exception.enums;

public enum ErrorMessage {
    ENTIDADE_NOT_FOUND("A entidade Cozinha de código %d não pôde ser encontrada"),
    ENTIDADE_EM_USO("A entidade Cozinha de código %d não pôde ser excluída, pois está em uso");

    String mensagem;

    private ErrorMessage(String mensagem){
        this.mensagem = mensagem;
    }

    public String get() {
        return mensagem;
    }
}
