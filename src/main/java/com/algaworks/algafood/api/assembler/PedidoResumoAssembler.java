package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResumoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    @Autowired
    ModelMapper modelMapper;

    PedidoResumoAssembler(){
        super(PedidoController.class, PedidoResumoDTO.class);
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido){
        final PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getId(), pedido);

        modelMapper.map(pedido, pedidoResumoDTO);

        pedidoResumoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoResumoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoResumoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());
        
        return pedidoResumoDTO;
    }

    public Pedido toDomainModel (PedidoDTO pedidoDTO ){
        return modelMapper.map(pedidoDTO, Pedido.class);
    }

    public List<PedidoResumoDTO> toListDTO (Collection<Pedido> pedidos){
        return pedidos.stream().map(this::toModel).toList();
    }
}
