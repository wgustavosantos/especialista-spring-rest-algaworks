package com.algaworks.algafood.domain.listener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
    
    @Autowired
    private EnvioEmailSerivce envioEmailSerivce;
    
//    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void aoConfirmarPedido(PedidoConfirmadoEvent event){
        final Pedido pedido = event.getPedido();
        EnvioEmailSerivce.Mensagem mensagem = EnvioEmailSerivce.Mensagem.builder()
                .assunto(pedido .getRestaurante().getNome() + " - Pedido confirmado.")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido )
                .destinatario(pedido .getCliente().getEmail())
                .build();

        envioEmailSerivce.enviar(mensagem);
    }
}
