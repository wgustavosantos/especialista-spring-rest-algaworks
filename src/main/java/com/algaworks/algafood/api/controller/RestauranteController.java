package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteAssembler;
import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.dto.inputDto.RestauranteInputDTO;
import com.algaworks.algafood.core.validation.ValidacaoException;
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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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

    @Autowired
    private SmartValidator smartValidator;

    @Autowired
    private RestauranteAssembler rAssembler;

    @Autowired
    private RestauranteInputDisassembler rInputDisassembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar
            (@RequestBody @Valid RestauranteInputDTO restauranteInput) {
        try {
            final Restaurante restaurante = rInputDisassembler.DTOtoDomainModel(restauranteInput);
            return rAssembler.toDTO(restauranteService.salvar(restaurante));

        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public List<RestauranteDTO> listar() {
        final List<Restaurante> restaurantes = restauranteService.listar();

        return rAssembler.toListDTO(restaurantes);
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        final Restaurante restaurante = restauranteService.buscar(restauranteId);

        return rAssembler.toDTO(restaurante)  ;
    }

    @PutMapping("/{id}")
    public RestauranteDTO atualizar(@RequestBody @Valid RestauranteInputDTO restauranteInput, @PathVariable Long id) {
        final Restaurante restauranteAtual;

        try {
            restauranteAtual = restauranteService.buscar(id);
            rInputDisassembler.copyProperties(restauranteInput, restauranteAtual);
            Cozinha cozinha = cozinhaService.buscar(restauranteInput.getCozinha().getId());
            restauranteAtual.setCozinha(cozinha);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

        return rAssembler.toDTO(restauranteService.atualizar(restauranteAtual));
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId) {
        restauranteService.deletar(restauranteId);
    }

    /**
     * @param servletRequest instancair um ServletServerHttpRequest e passar no argumento da exceção
     *                       HttpMessageNotReadableException no método merge e relançar para ser capturada por
     *                       handleHttpMessageNotReadable em ApiExceptionHandler
     */
    @PatchMapping("/{id}")
    public RestauranteDTO atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest servletRequest) {
        Restaurante restauranteAtual = restauranteService.buscar(id);

        merge(campos, restauranteAtual, servletRequest);
        validate(restauranteAtual, "restaurante");
        final Restaurante restaurante = restauranteService.atualizar(restauranteAtual);

        return rAssembler.toDTO(restaurante) ;
    }

    private void validate(Restaurante restaurante, String objectName){
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

        smartValidator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);

        }
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
        } catch (IllegalArgumentException ex) {
            final Throwable rootCause = ExceptionUtils.getRootCause(ex);

            final ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(servletRequest);
            throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, servletServerHttpRequest);
        }
    }
}
