package com.algaworks.algafood.domain.repository;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    FotoRecuperada recuperar(String nomeArquivo);

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;
    }

    @Builder
    @Getter
    class  FotoRecuperada {
        private InputStream inputStreamFoto;
        private String urlFoto;

        public boolean temUrl(){
            return urlFoto != null;
        }

        public boolean temInputStream(){
            return inputStreamFoto != null;
        }
    }
}
