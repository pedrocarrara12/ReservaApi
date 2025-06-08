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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class reservaService {
    private reservaRepository reservaRepository;
    private salaRepository salaRepository;
    private responsavelRepository responsavelRepository;

    public reservaService(com.reserva_hospedagem.com.projeto_hospedagem.repository.reservaRepository reservaRepository, com.reserva_hospedagem.com.projeto_hospedagem.repository.salaRepository salaRepository, com.reserva_hospedagem.com.projeto_hospedagem.repository.responsavelRepository responsavelRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.responsavelRepository = responsavelRepository;
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
        Reserva reserva = new Reserva(sala, responsavel, reservaDTO.horainicio(), reservaDTO.horaFim());
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
      return reservas;

    }
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorId(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            return reserva.get();
        } else {
            throw new RuntimeException("Nenhuma reserva com esse id encontrado");
        }
    }
    public Reserva atualizarReserva(Long id,ReservaDTO reservaDTO) {
       Reserva reserva1 = reservaRepository.findById(id).orElseThrow(()-> new RuntimeException("Reserava nao encontrada"));
       Sala sala = salaRepository.findById(reservaDTO.sala().getId()).orElseThrow(()->
               new RuntimeException("Sala nao encontrada"));
       Responsavel responsavel = responsavelRepository.findById(reservaDTO.responsavel().
               getId()).orElseThrow(()-> new RuntimeException("Responsavel nao encontrado"));

       if (reservaDTO.horaFim().isBefore(reservaDTO.horainicio())) {
           throw new IllegalArgumentException("Data/hora final deve ser após a inicial");
        }
       reserva1.setSala(sala);
       reserva1.setResponsavel(responsavel);
       reserva1.setDatahoraInicio(reservaDTO.horainicio());
       reserva1.setDatahoraFim(reservaDTO.horaFim());
       return reservaRepository.save(reserva1);
    }
    public List<Reserva> listarPorSala(Long salaId) {

        Sala sala = salaRepository.findById(salaId)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada com o ID: " + salaId));
        List<Reserva> reservas = reservaRepository.findBySalaId(salaId);
        if (reservas.isEmpty()) {
            throw new RuntimeException("Nenhuma reserva encontrada para a sala: " + sala.getId());
        }
        return reservas;
    };


}
