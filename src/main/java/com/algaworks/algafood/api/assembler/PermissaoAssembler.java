package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.dto.PermissaoDTO;
import com.algaworks.algafood.api.model.inputDto.ProdutoInputDTO;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PermissaoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDTO toDTO(Permissao permissao){
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    public List<PermissaoDTO> toListDTO(Collection<Permissao> permissoes){
        return permissoes.stream().map(this::toDTO).toList();
    }

    public Permissao toDomainModel(Object permissaoInputDTO){
        return modelMapper.map(permissaoInputDTO, Permissao.class);
    }

    public void copyProperties(ProdutoInputDTO produtoInputDTO, Produto produto){
        modelMapper.map(produtoInputDTO, produto);
    }
}
