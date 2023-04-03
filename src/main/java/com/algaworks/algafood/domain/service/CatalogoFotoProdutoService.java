package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
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
    public FotoProduto salvar(FotoProduto fotoProduto, InputStream inputStream){
        Long restauranteId = fotoProduto.getRestauranteId();
        final Long produtoId = fotoProduto.getProduto().getId();
        fotoProduto.gerarNomeArquivo();

        produtoRepository.
                findFotoById(restauranteId, produtoId)
                .ifPresent(foto -> produtoRepository.delete(foto));

        final FotoProduto fotoSalva = produtoRepository.save(fotoProduto);
        produtoRepository.flush();


        FotoStorageService.NovaFoto foto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(fotoProduto.getNomeArquivo())
                .inputStream(inputStream).build();

        fotoStorageService.armazenar(foto);

        return fotoSalva;
    }
}
