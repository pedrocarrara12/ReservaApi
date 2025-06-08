package com.reserva_hospedagem.com.projeto_hospedagem.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacidade;
    @ElementCollection
    private List<String> recursos;
    @Deprecated
    public Sala() {
    }

    public Sala(Integer capacidade, List<String> recursos) {
        this.capacidade = capacidade;
        this.recursos = recursos;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public List<String> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<String> recursos) {
        this.recursos = recursos;
    }

    public Long getId() {
        return id;
    }
}
