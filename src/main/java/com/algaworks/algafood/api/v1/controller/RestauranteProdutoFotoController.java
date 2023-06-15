package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.FotoProdutoAssembler;
import com.algaworks.algafood.api.v1.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.FotoProdutoInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoAssembler fTAssember;

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
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

        return fTAssember.toModel(fotoSalva);
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId,
                                 @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return fTAssember.toModel(fotoProduto);
    }

    @Override
    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId,
                                        @RequestHeader(name = "accept") String accepHeader) throws HttpMediaTypeNotAcceptableException {

            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
            MediaType mediaTypeFotoProduto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(accepHeader);

            verificarCompatibilidadeMediaType(mediaTypeFotoProduto, mediaTypesAceitas);

            try{
                final FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

                if(fotoRecuperada.temUrl()){
                    return ResponseEntity
                            .status(HttpStatus.FOUND)
                            .header(HttpHeaders.LOCATION, fotoRecuperada.getUrlFoto()).build();

                } else{
                    return ResponseEntity
                            .ok()
                            .contentType(mediaTypeFotoProduto)
                            .body(new InputStreamResource(fotoRecuperada.getInputStreamFoto()));
                }
        } catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
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
