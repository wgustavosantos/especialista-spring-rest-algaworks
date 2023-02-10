package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlWrapper {

    //criando um construtor que recebe no parâmetro uma lista de cozinhas
    @NonNull
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cozinha> cozinhas;
}
