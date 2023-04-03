package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.infrastructure.repository.service.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto, InputStream inputStream) {
        Long restauranteId = fotoProduto.getRestauranteId();
        final Long produtoId = fotoProduto.getProduto().getId();
        fotoProduto.gerarNomeArquivo();


        produtoRepository.
                findFotoById(restauranteId, produtoId)
                .ifPresent(foto -> {
                    fotoStorageService.remover(foto.getNomeArquivo());
                    produtoRepository.delete(foto);
                });

        final FotoProduto fotoSalva = produtoRepository.save(fotoProduto);


        FotoStorageService.NovaFoto foto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(fotoProduto.getNomeArquivo())
                .inputStream(inputStream).build();

        fotoStorageService.armazenar(foto);

        return fotoSalva;
    }

    public InputStream recuperar(FotoProduto fotoProduto) {
        Long restauranteId = fotoProduto.getRestauranteId();
        final Long produtoId = fotoProduto.getProduto().getId();

        final FotoProduto fotoProdutoAtual = buscarOuFalhar(restauranteId, produtoId);

        return fotoStorageService.recuperar(fotoProdutoAtual.getNomeArquivo());

    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(() -> new StorageException("Não foi possível localizar o arquivo."));
    }
}
