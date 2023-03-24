package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> salvar(List<ItemPedido> itensPedido){
        return itemPedidoRepository.saveAll(itensPedido);
    }
}
