package io.github.juniorjesustcc.service;

import io.github.juniorjesustcc.model.OrdemServico;
import io.github.juniorjesustcc.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    @Autowired
    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public List<OrdemServico> listarOrdensDoDia(LocalDate data) {
        return ordemServicoRepository.findByData(data);
    }

    public List<OrdemServico> listarOrdens() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> findById(Long id) {
        return ordemServicoRepository.findById(id);
    }

    public OrdemServico salvar(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public OrdemServico atualizarStatus(OrdemServico ordemServico, String novoStatus) {
        ordemServico.setStatus(novoStatus);
        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> filterByStatus(String status) {
        return ordemServicoRepository.findByStatus(status);
    }

    public List<OrdemServico> filterByDateAndStatus(LocalDate date, String status) { // Novo método
        if(date != null && status != null && !status.isEmpty()) {
            return ordemServicoRepository.findByDataAndStatus(date, status);
        } else if (date != null) {
            return ordemServicoRepository.findByData(date);
        } else if (status != null && !status.isEmpty()) {
            return ordemServicoRepository.findByStatus(status);
        } else {
            return ordemServicoRepository.findAll();
        }
    }

}