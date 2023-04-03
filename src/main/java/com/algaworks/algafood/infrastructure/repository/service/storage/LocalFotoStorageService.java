package com.algaworks.algafood.infrastructure.repository.service.storage;

import com.algaworks.algafood.domain.repository.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${algafood.storage.local.diretorio-fotos}")
    private Path path;

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            final Path arquivoPath = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível deletar arquivo.", e);
        }
    }

    @Override
    public InputStream recuperar(String nomeArquivo) {
        InputStream aquivoRecuperado;
        try {
            final Path arquivoPath = getArquivoPath(nomeArquivo);

            aquivoRecuperado = Files.newInputStream(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }

        return aquivoRecuperado;
    }

    private Path getArquivoPath(String nomeArquivo) {
        return path.resolve(Path.of(nomeArquivo));
    }
}
