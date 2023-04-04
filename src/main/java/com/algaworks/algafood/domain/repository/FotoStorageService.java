package com.algaworks.algafood.domain.repository;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    InputStream recuperar(String nomeArquivo);

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;
    }
}
