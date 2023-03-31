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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable){
        final Page<Pedido> pedidosPage = pedidoService.pesquisar(pedidoFilter, pageable);
        final List<PedidoResumoDTO> pedidosResumoDTO = pRAssembler.toListDTO(pedidosPage.getContent());
        final Page<PedidoResumoDTO> pedidoResumoDTOS = new PageImpl<>(pedidosResumoDTO, pageable, pedidosPage.getTotalElements());
        return pedidoResumoDTOS;
    }


    @GetMapping("/{codigoId}")
    public PedidoDTO buscar(@PathVariable String codigoId){
        return pAssembler.toDTO(pedidoService.buscar(codigoId));
    }
}
