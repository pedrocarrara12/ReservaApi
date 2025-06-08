package com.reserva_hospedagem.com.projeto_hospedagem.repository;

import com.reserva_hospedagem.com.projeto_hospedagem.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface responsavelRepository extends JpaRepository <Responsavel,Long> {
    boolean existsByNome(String nome);
}
