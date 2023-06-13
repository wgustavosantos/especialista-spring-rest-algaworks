package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.dto.PedidoDTO;
import com.algaworks.algafood.api.v1.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.PedidoInputDTO;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.repository.data.PageableTranslator;
import com.google.common.collect.ImmutableMap;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface PedidoControllerOpenApi {

    @ResponseStatus(HttpStatus.CREATED)
    PedidoDTO adicionar(@RequestBody @Valid PedidoInputDTO pedidoInputDTO);

    PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable);

    PedidoDTO buscar(@PathVariable String codigoId);

    default Pageable truduzirPageable(Pageable apiPageable) {
        final ImmutableMap<String, String> mapFields = ImmutableMap.of("codigo", "codigo", "restaurante.nome", "restaurante.nome", "nomeCliente", "cliente.nome", "valortotal", "valorTotal");
        return PageableTranslator.translate(apiPageable, mapFields);
    }
}
