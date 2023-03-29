package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.dto.inputDto.PedidoInputDTO;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.PedidoService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoAssembler pAssembler;

    @Autowired
    private PedidoResumoAssembler pRAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO  adicionar(@RequestBody @Valid PedidoInputDTO pedidoInputDTO){

        final Pedido pedido = pAssembler.toDomainModel(pedidoInputDTO);

       return pAssembler.toDTO(pedidoService.adicionar(pedido));
    }

    @GetMapping
    public MappingJacksonValue listar(@RequestParam String campos){
        final List<PedidoResumoDTO> pedidos = pRAssembler.toListDTO(pedidoService.listar());

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(pedidos);
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
        simpleFilterProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.serializeAll());
        if(StringUtils.isNotBlank(campos)){
            simpleFilterProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }
        
        mappingJacksonValue.setFilters(simpleFilterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId){
        return pAssembler.toDTO(pedidoService.buscar(pedidoId));
    }
}
