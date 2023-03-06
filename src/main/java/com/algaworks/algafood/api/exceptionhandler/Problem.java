package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/*Classe criada para representar a mensagem de erro da exceção de acordo com a RFC 7807*/
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    /*Extensão da especificação*/
    private String userMessage;
    private LocalDateTime timeStamp;
    /*Extensão cap 9 - aula 4 */
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}
