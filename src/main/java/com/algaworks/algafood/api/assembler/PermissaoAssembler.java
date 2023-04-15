package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.model.dto.PermissaoDTO;
import com.algaworks.algafood.api.model.inputDto.ProdutoInputDTO;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoAssembler implements RepresentationModelAssembler<Permissao, PermissaoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    public PermissaoDTO toModel(Permissao permissao){
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    @Override
    public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(algaLinks.linkToPermissoes());
    }

    public Permissao toDomainModel(Object permissaoInputDTO){
        return modelMapper.map(permissaoInputDTO, Permissao.class);
    }

    public void copyProperties(ProdutoInputDTO produtoInputDTO, Produto produto){
        modelMapper.map(produtoInputDTO, produto);
    }
}
