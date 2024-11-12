package io.github.juniorjesustcc.repository;

import io.github.juniorjesustcc.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    List<OrdemServico> findByStatus(String status);
    List<OrdemServico> findByData(LocalDate date);
    List<OrdemServico> findByDataAndStatus(LocalDate date, String status);
}