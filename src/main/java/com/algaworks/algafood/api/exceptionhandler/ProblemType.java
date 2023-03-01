package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

/**
 * Enum utilizada para compor o corpo da exceção em formalidade com a RFC 7807
 */
@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),

    CORPO_NAO_LEGIVEL("/corpo-nao-legivel", "Corpo não legível");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "http://localhost:8080" + path;
        this.title = title;

    }
}
