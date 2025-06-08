package com.reserva_hospedagem.com.projeto_hospedagem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    private String nome;
    @Deprecated
    public Responsavel() {
    }

    public Responsavel(List<Reserva> reservas, String nome) {
        this.reservas = reservas;
        this.nome = nome;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
}
