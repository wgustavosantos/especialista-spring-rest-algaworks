package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EnderecoMixin {

    @JsonIgnore
    private Cidade cidade;
}
