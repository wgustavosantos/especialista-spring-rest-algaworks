package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.FotoProdutoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RestauranteProdutoFotoControllerOpenApi {

    FotoProdutoDTO atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput, MultipartFile foto) throws IOException;

    void deletarFotoProduto(Long restauranteId, Long produtoId);

    ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
    FotoProdutoDTO buscar(Long restauranteId, Long produtoId);
}