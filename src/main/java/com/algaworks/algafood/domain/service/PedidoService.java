package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlgaSecurity algaSecurity;

    @Transactional
    public Pedido adicionar(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(algaSecurity.getUsuarioId());

        final Restaurante restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
        final FormaPagamento formaPagamento = formaPagamentoService.buscar(pedido.getFormaPagamento().getId());

        if(restaurante.naoAceitaFormaPagamento(formaPagamento))
            throw new NegocioException(String.format(ErrorMessage.FORMA_PAGAMENTO_NAO_ACEITA.get(),
                    formaPagamento.getDescricao(), restaurante.getNome()));


        final Cidade cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidade().getId());
        final Usuario cliente = usuarioService.buscar(pedido.getCliente().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setCliente(cliente);
    }

    public void validarItens(Pedido pedido){

        final Long restauranteId = pedido.getRestaurante().getId();
        final BigDecimal taxaFrete = pedido.getRestaurante().getTaxaFrete();

        pedido.getItensPedido().forEach(iP ->{
            final Produto produto = produtoService.buscar(iP.getProduto().getId(), restauranteId);
            iP.setPrecoUnitario(produto.getPreco());
            iP.setProduto(produto);
            iP.setPedido(pedido);
            iP.calcularPrecoTotal();
        });
        pedido.setTaxaFrete(taxaFrete);
        pedido.calcularValorTotal();
    }

    public Pedido buscar(String codigoId){
        return buscarOuFalhar(codigoId);
    }

    private Pedido buscarOuFalhar(String codigoId){
        return pedidoRepository.findByCodigo(codigoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException((Object) codigoId));
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Page<Pedido> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
        return pedidoRepository.findAll(PedidoSpecs.filtroPedidoSpec(pedidoFilter), pageable);
    }
}
