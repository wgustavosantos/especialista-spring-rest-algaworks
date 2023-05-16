package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.GrupoInputDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public GrupoAssembler() {
        super(GrupoController.class, GrupoDTO.class);
    }


    @Override
    public GrupoDTO toModel(Grupo grupo) {
        GrupoDTO grupoDTO = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoDTO);

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            grupoDTO.add(algaLinks.linkToGrupos("grupos"));

            grupoDTO.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        }

        return grupoDTO;
    }

    @Override
    public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> entities) {
        CollectionModel<GrupoDTO> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(algaLinks.linkToGrupos());
        }

        return collectionModel;
    }

    public Grupo toDomainModel (GrupoInputDTO grupoInputDTO){
        return modelMapper.map(grupoInputDTO, Grupo.class);
    }

    public void copyProperties (GrupoInputDTO grupoInputDTO, Grupo grupo){
        modelMapper.map(grupoInputDTO, grupo);
    }
}
