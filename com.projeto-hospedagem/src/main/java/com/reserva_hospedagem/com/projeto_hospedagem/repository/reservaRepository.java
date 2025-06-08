package com.reserva_hospedagem.com.projeto_hospedagem.repository;

import com.reserva_hospedagem.com.projeto_hospedagem.entity.Reserva;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Responsavel;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface reservaRepository extends JpaRepository<Reserva,Long> {
    boolean existsByDatahoraInicio(LocalDateTime localDateTime);
    boolean existsBySalaAndDatahoraInicioLessThanAndDatahoraFimGreaterThan(
            Sala sala,
            LocalDateTime fim,
            LocalDateTime inicio
    );
    List<Reserva>findByResponsavelId(Long id);
    List<Reserva> findBySalaId(Long salaId);
}
