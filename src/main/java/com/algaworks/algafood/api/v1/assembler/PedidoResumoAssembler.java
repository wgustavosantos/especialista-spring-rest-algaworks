package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.dto.PedidoDTO;
import com.algaworks.algafood.api.v1.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PedidoResumoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    PedidoResumoAssembler(){
        super(PedidoController.class, PedidoResumoDTO.class);
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido){
        final PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoResumoDTO);
        if (algaSecurity.podePesquisarPedidos()) {
            pedidoResumoDTO.add(algaLinks.linkToPedidos("pedidos"));
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            pedidoResumoDTO.getRestaurante().add(
                    algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoResumoDTO.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
        }
        
        return pedidoResumoDTO;
    }

    public Pedido toDomainModel (PedidoDTO pedidoDTO ){
        return modelMapper.map(pedidoDTO, Pedido.class);
    }

    public List<PedidoResumoDTO> toListDTO (Collection<Pedido> pedidos){
        return pedidos.stream().map(this::toModel).toList();
    }
}
