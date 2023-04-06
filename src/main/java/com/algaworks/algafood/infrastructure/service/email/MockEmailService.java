package com.algaworks.algafood.infrastructure.service.email;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailSerivce;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

@Slf4j
public class MockEmailService implements EnvioEmailSerivce {
    @Override
    public void enviar(Mensagem mensagem) {

        final Set<String> destinatarios = mensagem.getDestinatarios();

        final String destinatario = destinatarios.stream().findFirst().get();
        final Map<String, Object> variaveis = mensagem.getVariaveis();
        final Pedido pedido = (Pedido) variaveis.get("pedido");

        log.info("Alteração de Pedido");
        log.info(mensagem.getAssunto());
        log.info("Destinarário: " + destinatario);
        log.info("Olá, " + pedido.getCliente().getNome() + ", seu pedido foi confirmado pelo restaurante e já está sendo preparado.");
        log.info("O pedido de código " + pedido.getId() + " foi confirmado!");

        pedido.getItensPedido().forEach(item -> {
           log.info("Quantidade \t\t Produto");
           log.info(item.getQuantidade() + "\t\t" + item.getProduto().getNome());
        });

        log.info(String.format("Taxa frete: R$ %.2f", pedido.getRestaurante().getTaxaFrete()));
        log.info(String.format("Subtotal: R$ %.2f", pedido.getSubTotal()));
        log.info(String.format("Valor total: R$ %.2f", pedido.getValorTotal()));
    }
}
