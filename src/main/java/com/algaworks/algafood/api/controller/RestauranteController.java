package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CozinhaService cozinhaService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid Restaurante restaurante) {

        try {
            Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);

        }catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Restaurante> listar() {

        return restauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {

        return restauranteService.buscar(restauranteId);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {

        try {
            Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
        } catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }

        return restauranteService.atualizar(restaurante, id);
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId) {
            restauranteService.deletar(restauranteId);
    }

    /**
     *
     *
     * @param servletRequest instancair um ServletServerHttpRequest e passar no argumento da exceção
     *                       HttpMessageNotReadableException no método merge e relançar para ser capturada por
     *                       handleHttpMessageNotReadable em ApiExceptionHandler
     */
    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest servletRequest) {
        Restaurante restauranteAtual = restauranteService.buscar(id);

        merge(campos, restauranteAtual, servletRequest);

        return atualizar(restauranteAtual, id);
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest servletRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            final Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);


            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                assert field != null;
                field.setAccessible(true);

                final Object novoValorConvertido = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValorConvertido);
            });
        } catch (IllegalArgumentException ex){
            final Throwable rootCause = ExceptionUtils.getRootCause(ex);

            final ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(servletRequest);
            throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, servletServerHttpRequest );
        }
    }
}
