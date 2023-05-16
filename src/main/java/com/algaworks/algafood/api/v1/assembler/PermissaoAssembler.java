package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.ProdutoInputDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    @Override
    public PermissaoDTO toModel(Permissao permissao){
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    @Override
    public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
        CollectionModel<PermissaoDTO> collectionModel
                = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(algaLinks.linkToPermissoes());
        }

        return collectionModel;
    }

    public Permissao toDomainModel(Object permissaoInputDTO){
        return modelMapper.map(permissaoInputDTO, Permissao.class);
    }

    public void copyProperties(ProdutoInputDTO produtoInputDTO, Produto produto){
        modelMapper.map(produtoInputDTO, produto);
    }
}
