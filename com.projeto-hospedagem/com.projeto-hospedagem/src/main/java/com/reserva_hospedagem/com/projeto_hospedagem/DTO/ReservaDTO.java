package com.reserva_hospedagem.com.projeto_hospedagem.DTO;

import com.reserva_hospedagem.com.projeto_hospedagem.entity.Responsavel;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Sala;

import java.time.LocalDateTime;

public record ReservaDTO(Sala sala, Responsavel responsavel, LocalDateTime horainicio, LocalDateTime horaFim) {
}
