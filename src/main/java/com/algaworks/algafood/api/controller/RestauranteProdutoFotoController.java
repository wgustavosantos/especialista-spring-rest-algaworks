package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.inputDto.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId,
                              @PathVariable Long produtoId,
                              @Valid FotoProdutoInput fotoProdutoInput){
        final String originalFilename = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getFile().getOriginalFilename();

        final Path path = Path.of("C:\\Users\\Guto1\\Documents\\dev\\" + originalFilename);

        System.out.println(path);
        System.out.println( fotoProdutoInput.getFile().getContentType());

        try {
            fotoProdutoInput.getFile().transferTo(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
