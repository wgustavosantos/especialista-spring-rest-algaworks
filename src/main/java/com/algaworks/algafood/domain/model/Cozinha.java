package com.algaworks.algafood.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cozinha")
public class Cozinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//provedor de persistencia
    private Long id;

    @Column(name = "nome", length = 30)
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return Objects.equals(id, cozinha.id) && Objects.equals(nome, cozinha.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
