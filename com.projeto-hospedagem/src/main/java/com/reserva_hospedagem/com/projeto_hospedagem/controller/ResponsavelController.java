package com.reserva_hospedagem.com.projeto_hospedagem.controller;

import com.reserva_hospedagem.com.projeto_hospedagem.DTO.ResponsavelDTO;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Responsavel;
import com.reserva_hospedagem.com.projeto_hospedagem.service.ResponsavelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    private ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }
    @PostMapping("/criar-responsavel")
    public Responsavel criarResponsavel(ResponsavelDTO responsavelDTO) {
        return responsavelService.criarResponsavel(responsavelDTO);
    }
    @GetMapping("/responsavel/{id}")
    public Responsavel buscarPorId(@PathVariable Long id) {
        return responsavelService.buscarPorId(id);
    }
    @PutMapping("/atualizar-responsavel/{id}")
    public void atualizarResponsavel(@PathVariable Long id,ResponsavelDTO responsavelDTO) {
        responsavelService.atualizarResponsavel(id,responsavelDTO);
    }
    @DeleteMapping("/deletar-responsavel/{id}")
    public void deletarResponsavel(@PathVariable Long id) {
        responsavelService.deletarResponsavel(id);
    }

}
