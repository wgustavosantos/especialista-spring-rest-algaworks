package com.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailSerivce {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem{
        @Singular
        private Set<String> destinatarios;
        private String assunto;
        private String corpo;

        @Singular("variavel")
        private Map<String, Object> variaveis;
    }
}