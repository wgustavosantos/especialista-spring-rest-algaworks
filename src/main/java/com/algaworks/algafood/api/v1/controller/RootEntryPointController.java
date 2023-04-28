package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v2.AlgaLinksV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaLinksV2 algaLinksV2;

    @GetMapping
    public RootEntryPointDTO root() {
        var rootEntryPointDTO = new RootEntryPointDTO();

        rootEntryPointDTO.add(algaLinks.linkToCozinhas("cozinhas"));
        rootEntryPointDTO.add(algaLinks.linkToPedidos("pedidos"));
        rootEntryPointDTO.add(algaLinks.linkToRestaurantes("restaurantes"));
        rootEntryPointDTO.add(algaLinks.linkToGrupos("grupos"));
        rootEntryPointDTO.add(algaLinks.linkToUsuarios("usuarios"));
        rootEntryPointDTO.add(algaLinks.linkToPermissoes("permissoes"));
        rootEntryPointDTO.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointDTO.add(algaLinks.linkToEstados("estados"));
        rootEntryPointDTO.add(algaLinks.linkToCidades("cidades"));
        rootEntryPointDTO.add(algaLinksV2.linkToCidades("cidades"));
        rootEntryPointDTO.add(algaLinks.linkToEstatisticas("estatisticas"));

        return rootEntryPointDTO;
    }

    private static class RootEntryPointDTO extends RepresentationModel<RootEntryPointDTO> {
    }
}
