package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@Entity
@Table(name = "cozinha")
public class Cozinha {

    @NotNull(groups = Groups.CozinhaId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//provedor de persistencia
    private Long id;

    @NotBlank
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();
}
