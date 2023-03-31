package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoAssembler;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.dto.inputDto.PedidoInputDTO;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter){
        final List<PedidoResumoDTO> pedidos = pRAssembler.toListDTO(pedidoService.pesquisar(pedidoFilter));

//13.2. Limitando os campos retornados pela API com @JsonFilter do Jackson
//terça-feira, 28 de março de 2023
//15:28
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(pedidos);
//        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
//        simpleFilterProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.serializeAll());
//        if(StringUtils.isNotBlank(campos)){
//            simpleFilterProvider.addFilter("pedidosFilter", SimpleBeanPropertyFilter.
//                    filterOutAllExcept(campos.split(",")));
//        }
//
//        mappingJacksonValue.setFilters(simpleFilterProvider);
        return pedidos;
    }


    @GetMapping("/{codigoId}")
    public PedidoDTO buscar(@PathVariable String codigoId){
        return pAssembler.toDTO(pedidoService.buscar(codigoId));
    }
}
