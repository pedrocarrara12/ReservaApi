package com.reserva_hospedagem.com.projeto_hospedagem.service;

import com.reserva_hospedagem.com.projeto_hospedagem.DTO.ResponsavelDTO;
import com.reserva_hospedagem.com.projeto_hospedagem.entity.Responsavel;
import com.reserva_hospedagem.com.projeto_hospedagem.repository.responsavelRepository;

import java.util.List;

public class ResponsavelService {
    private  responsavelRepository responsavelRepository;

    public ResponsavelService(responsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }
    public Responsavel criarResponsavel(ResponsavelDTO responsavelDTO) {
        if (responsavelDTO.nome().length()<2) {
            throw new RuntimeException("Nome invalido");
        } else if (responsavelDTO.nome().matches(".*\\d.*")) {
            throw new RuntimeException("Nome inválido: não pode conter números");
        }
        Responsavel responsavel = new Responsavel(responsavelDTO.reservas(),responsavelDTO.nome());
        return responsavelRepository.save(responsavel);
    }
    public Responsavel buscarPorId(Long id) {
        return  responsavelRepository.findById(id).orElseThrow(()-> new RuntimeException("Nao encontrado responsavel"));
    }
    public Responsavel atualizarResponsavel(Long id, ResponsavelDTO novosDados) {
        Responsavel responsavel = buscarPorId(id);
        responsavel.setNome(novosDados.getNome());
        return responsavelRepository.save(responsavel);
    }
    public void deletarResponsavel(Long id) {
        Responsavel responsavel = buscarPorId(id);
        responsavelRepository.delete(responsavel);
    }
    public List<Responsavel> listarTodosResponsaveis() {
       return responsavelRepository.findAll();
    }
}
