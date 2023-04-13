package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.inputDto.PedidoInputDTO;
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
public class PedidoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    @Autowired
    ModelMapper modelMapper;

    public PedidoAssembler() {
        super(PedidoController.class, PedidoDTO.class);
    }

    @Override
    public PedidoDTO toModel(Pedido pedido){
        final PedidoDTO pedidoDTO = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoDTO);


        pedidoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

        pedidoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoDTO.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoDTO.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoDTO.getItensPedido().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoDTO.getRestaurante().getId(), item.getId()))
                    .withRel("produto"));
        });
        
        return pedidoDTO;
    }

    public Pedido toDomainModel (PedidoInputDTO pedidoInputDTO ){
        return modelMapper.map(pedidoInputDTO, Pedido.class);
    }

    public List<PedidoDTO> toListDTO (Collection<Pedido> pedidos){
        return pedidos.stream().map(this::toModel).toList();
    }
}
