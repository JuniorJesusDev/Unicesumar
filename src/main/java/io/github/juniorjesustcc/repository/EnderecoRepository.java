package io.github.juniorjesustcc.repository;

import io.github.juniorjesustcc.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByRuaContainingIgnoreCase(String rua);
    List<Endereco> findByUltimoAtendimentoBefore(LocalDate data);
}
