package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.inputDto.PedidoInputDTO;
import com.algaworks.algafood.api.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.PedidoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Override
    @GetMapping
    public PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable){
        pageable = truduzirPageable(pageable);
        final Page<Pedido> pedidosPage = pedidoService.pesquisar(pedidoFilter, pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pRAssembler);
    }

    @Override
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula"
                    , name = "campos", paramType = "query", type = "string")
    })
    @GetMapping("/{codigoId}")
    public PedidoDTO buscar(@PathVariable String codigoId){
        return pAssembler.toModel(pedidoService.buscar(codigoId));
    }

}
