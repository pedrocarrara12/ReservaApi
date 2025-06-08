package com.reserva_hospedagem.com.projeto_hospedagem.DTO;

import com.reserva_hospedagem.com.projeto_hospedagem.entity.Reserva;


import java.util.List;

public record ResponsavelDTO(
        List<Reserva> reservas,
       String nome) {
}
