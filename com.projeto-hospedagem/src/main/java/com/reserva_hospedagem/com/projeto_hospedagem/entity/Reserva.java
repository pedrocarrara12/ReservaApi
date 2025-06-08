package com.reserva_hospedagem.com.projeto_hospedagem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
    @ManyToOne
    @JoinColumn(name = "responsavel_id",nullable = false)
    private Responsavel responsavel;

    private LocalDateTime datahoraInicio;
    private LocalDateTime datahoraFim;

    @Deprecated
    public Reserva() {
    }

    public Reserva(Sala sala, Responsavel responsavel, LocalDateTime datahoraInicio, LocalDateTime datahoraFim) {
        this.sala = sala;
        this.responsavel = responsavel;
        this.datahoraInicio = datahoraInicio;
        this.datahoraFim = datahoraFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getDatahoraInicio() {
        return datahoraInicio;
    }

    public void setDatahoraInicio(LocalDateTime datahoraInicio) {
        this.datahoraInicio = datahoraInicio;
    }

    public LocalDateTime getDatahoraFim() {
        return datahoraFim;
    }

    public void setDatahoraFim(LocalDateTime datahoraFim) {
        this.datahoraFim = datahoraFim;
    }
}
