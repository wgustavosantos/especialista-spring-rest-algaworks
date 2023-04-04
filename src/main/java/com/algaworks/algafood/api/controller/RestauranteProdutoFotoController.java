package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FotoProdutoAssember;
import com.algaworks.algafood.api.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.model.inputDto.FotoProdutoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoAssember fTAssember;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId,
                                        @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        FotoProduto fotoProduto = new FotoProduto();
        final MultipartFile file = fotoProdutoInput.getFile();

        fotoProduto.setProduto(produtoService.buscar(produtoId, restauranteId));
        fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
        fotoProduto.setContentType(file.getContentType());
        fotoProduto.setTamanho(file.getSize());
        fotoProduto.setNomeArquivo(file.getOriginalFilename());

        final FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(fotoProduto, fotoProdutoInput.getFile().getInputStream());

        return fTAssember.toDTO(fotoSalva);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return fTAssember.toDTO(fotoProduto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId,
                                                          @PathVariable Long produtoId,
                                                          @RequestHeader(name = "accept") String accepHeader) throws HttpMediaTypeNotAcceptableException {

            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
            MediaType mediaTypeFotoProduto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(accepHeader);

            verificarCompatibilidadeMediaType(mediaTypeFotoProduto, mediaTypesAceitas);

            try{
            final InputStream inputStream = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            return ResponseEntity
                    .ok()
                    .contentType(mediaTypeFotoProduto)
                    .body(new InputStreamResource(inputStream));
        } catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public void deletarFotoProduto(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId){

        catalogoFotoProdutoService.deletar(restauranteId, produtoId);

    }

    private void verificarCompatibilidadeMediaType(MediaType mediaType,
                                                   List<MediaType> mediaTypesAceitas)
            throws HttpMediaTypeNotAcceptableException {
        /*false para não compativel com algum mediatype da lista*/
        final boolean compativel = mediaTypesAceitas.stream().anyMatch(m -> m.isCompatibleWith(mediaType));

        if(!compativel){/*false vira true e true vira fase*/
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }

}
