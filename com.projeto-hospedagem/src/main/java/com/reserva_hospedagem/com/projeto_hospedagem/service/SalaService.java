package com.reserva_hospedagem.com.projeto_hospedagem.service;

import com.reserva_hospedagem.com.projeto_hospedagem.DTO.SalaDTO;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Sala;
import com.reserva_hospedagem.com.projeto_hospedagem.exceptions.NenhumaSalaException;
import com.reserva_hospedagem.com.projeto_hospedagem.repository.salaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private salaRepository salaRepository;

    public SalaService(salaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }
    public List<Sala> listarTodasSalas() {
        List<Sala> salas = salaRepository.findAll();
        if (salas.isEmpty()) {
            throw new NenhumaSalaException("Nenhuma sala ");
        }
        return salas;
    }
    public Sala buscarSalaPorId(Long id) {
        Sala salaEncontrada = salaRepository.findById(id).orElseThrow(()->
                new NenhumaSalaException("Nenhuma Sala encontrada conm esse id"));
        return salaEncontrada;
    }
    public Sala cadastrarSala(SalaDTO salaDTO) {



    }

}
