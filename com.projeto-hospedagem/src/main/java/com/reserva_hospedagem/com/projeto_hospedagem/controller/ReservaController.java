package com.reserva_hospedagem.com.projeto_hospedagem.controller;

import com.reserva_hospedagem.com.projeto_hospedagem.DTO.ReservaDTO;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Reserva;
import com.reserva_hospedagem.com.projeto_hospedagem.repository.reservaRepository;
import com.reserva_hospedagem.com.projeto_hospedagem.service.reservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public
class ReservaController {

   private reservaService reservaService;

    public ReservaController(reservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/listar-todas")
    public List<Reserva> listarTodasReservas() {
        return reservaService.listarTodas();
    }
    @PostMapping("/criarReserva")
    public Reserva criarReserva(@RequestBody ReservaDTO reserva) {
        return reservaService.criarReserva(reserva);
    }
    @PutMapping("/atualizar-reserva/{id}")
    public Reserva atualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reserva) {
        return reservaService.atualizarReserva(id,reserva);
    }
    @DeleteMapping("deletar/{id}")
    public void deletarReserva (@PathVariable Long id) {
        reservaService.deletarReserva(id);
    }

}
