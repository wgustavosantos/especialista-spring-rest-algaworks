package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
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
}
