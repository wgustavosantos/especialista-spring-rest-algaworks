package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.PedidoAssembler;
import com.algaworks.algafood.api.v1.assembler.PedidoResumoAssembler;
import com.algaworks.algafood.api.v1.model.dto.PedidoDTO;
import com.algaworks.algafood.api.v1.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.PedidoInputDTO;
import com.algaworks.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoAssembler pAssembler;

    @Autowired
    private PedidoResumoAssembler pRAssembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO  adicionar(@RequestBody @Valid PedidoInputDTO pedidoInputDTO){

        final Pedido pedido = pAssembler.toDomainModel(pedidoInputDTO);

       return pAssembler.toModel(pedidoService.adicionar(pedido));
    }

    @CheckSecurity.Pedidos.PodePesquisar
    @Override
    @GetMapping
    public PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable){
        Pageable pageableTraduzido = truduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoService.pesquisar(pedidoFilter, pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pRAssembler);
    }

    @CheckSecurity.Pedidos.PodeBuscar
    @Override
    @GetMapping("/{codigoId}")
    public PedidoDTO buscar(@PathVariable String codigoId){
        return pAssembler.toModel(pedidoService.buscar(codigoId));
    }

}
