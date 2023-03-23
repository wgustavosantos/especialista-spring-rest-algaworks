package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PedidoResumoAssembler {

    @Autowired
    ModelMapper modelMapper;

    public PedidoResumoDTO toDTO (Pedido pedido){
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public Pedido toDomainModel (PedidoDTO pedidoDTO ){
        return modelMapper.map(pedidoDTO, Pedido.class);
    }

    public List<PedidoResumoDTO> toListDTO (Collection<Pedido> pedidos){
        return pedidos.stream().map(this::toDTO).toList();
    }
}
