package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.inputDto.RestauranteInputDTO;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO adicionar
            (@RequestBody @Valid RestauranteInputDTO restauranteInput) {
        try {
            return toDTO(restauranteService.salvar(toDomainModel(restauranteInput)));

        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public List<RestauranteDTO> listar() {
        final List<Restaurante> restaurantes = restauranteService.listar();

        return restaurantes.stream().map(RestauranteController::toDTO).toList();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        final Restaurante restaurante = restauranteService.buscar(restauranteId);

        return toDTO(restaurante);
    }

    @PutMapping("/{id}")
    public RestauranteDTO atualizar(@RequestBody @Valid Restaurante restaurante, @PathVariable Long id) {

        try {
            Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

        return toDTO(restauranteService.atualizar(restaurante, id));
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

        return atualizar(restauranteAtual, id);
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

    private static RestauranteDTO toDTO(Restaurante restaurante) {
        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO restauranteDTO = new RestauranteDTO();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
    }

    private Restaurante toDomainModel(RestauranteInputDTO restauranteInput) {

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }


}
