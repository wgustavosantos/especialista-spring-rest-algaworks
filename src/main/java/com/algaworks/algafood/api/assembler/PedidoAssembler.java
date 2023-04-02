package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.inputDto.PedidoInputDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PedidoAssembler {

    @Autowired
    ModelMapper modelMapper;

    public PedidoDTO toDTO (Pedido pedido){
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public Pedido toDomainModel (PedidoInputDTO pedidoInputDTO ){
        return modelMapper.map(pedidoInputDTO, Pedido.class);
    }

    public List<PedidoDTO> toListDTO (Collection<Pedido> pedidos){
        return pedidos.stream().map(this::toDTO).toList();
    }
}
