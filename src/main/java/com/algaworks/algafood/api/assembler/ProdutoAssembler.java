package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.model.dto.ProdutoDTO;
import com.algaworks.algafood.api.model.inputDto.ProdutoInputDTO;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public ProdutoAssembler() {
        super(RestauranteProdutoController.class, ProdutoDTO.class);
    }

    @Override
    public ProdutoDTO toModel(Produto produto){
        ProdutoDTO produtoDTO = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());

        modelMapper.map(produto, produtoDTO);

        produtoDTO.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        return produtoDTO;
    }

    public List<ProdutoDTO> toListDTO(List<Produto> produtos){
        return produtos.stream().map(this::toModel).toList();
    }

    public Produto toDomainModel(ProdutoInputDTO produtoInputDTO){
        return modelMapper.map(produtoInputDTO, Produto.class);
    }

    public void copyProperties(ProdutoInputDTO produtoInputDTO, Produto produto){
        modelMapper.map(produtoInputDTO, produto);
    }
}
