package com.reserva_hospedagem.com.projeto_hospedagem.service;

import com.reserva_hospedagem.com.projeto_hospedagem.DTO.ReservaDTO;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Reserva;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Responsavel;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Sala;
import com.reserva_hospedagem.com.projeto_hospedagem.exceptions.ExisteReservaHorarioException;
import com.reserva_hospedagem.com.projeto_hospedagem.repository.reservaRepository;
import com.reserva_hospedagem.com.projeto_hospedagem.repository.responsavelRepository;
import com.reserva_hospedagem.com.projeto_hospedagem.repository.salaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class reservaService {
    private reservaRepository reservaRepository;
    private salaRepository salaRepository;
    private responsavelRepository responsavelRepository;
    public reservaService(com.reserva_hospedagem.com.projeto_hospedagem.repository.reservaRepository reservaRepository, com.reserva_hospedagem.com.projeto_hospedagem.repository.salaRepository salaRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
    }

    public boolean existeReservaHorario(LocalDateTime localDateTime) {
        if (reservaRepository.existsByDatahoraInicio(localDateTime)) {
            throw new ExisteReservaHorarioException("Já existe reserva com esse horario");
        }
        return true;
    }
    public Reserva criarReserva(ReservaDTO reservaDTO) {
        Sala sala = salaRepository.findById(reservaDTO.sala().getId()).orElseThrow(()-> new RuntimeException("Sala nao encontrada"));

        Responsavel responsavel = responsavelRepository.findById(reservaDTO.responsavel().getId()).orElseThrow(()-> new RuntimeException("Responsavel nao enctrado"));

        if (reservaDTO.horaFim().isBefore(reservaDTO.horainicio())) {
            throw new IllegalArgumentException("Data hoa final nao pode ser antes da data hora inicial");
        }
        Reserva reserva = new Reserva(reservaDTO.sala(),reservaDTO.responsavel(),reservaDTO.horainicio(),reservaDTO.horaFim());
        return reservaRepository.save(reserva);
    }
    public void deletarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro!!! Nada encontrado"));

        reservaRepository.delete(reserva);
    }
    public List<Reserva> listarReservasPorResponsavelId(Long id) {
       List<Reserva> reservas = reservaRepository.findByResponsavelId(id);
       if (reservas.isEmpty()) {
           throw new RuntimeException("Nenhuma reserva encontrada para o Responsavel de Id " + id);
       }


    }
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }


}
