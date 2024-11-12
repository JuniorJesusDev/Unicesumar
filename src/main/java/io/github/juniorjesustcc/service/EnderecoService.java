package io.github.juniorjesustcc.service;

import io.github.juniorjesustcc.model.Endereco;
import io.github.juniorjesustcc.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarEnderecoPeloId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o id: " + id));
    }

    public List<Endereco> buscarPorNomeRua(String nomeRua) {
        return enderecoRepository.findByRuaContainingIgnoreCase(nomeRua);
    }

    public void salvar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public List<Endereco> buscarEnderecosPendentes() {
        LocalDate umAnoAtras = LocalDate.now().minusYears(1);
        return enderecoRepository.findByUltimoAtendimentoBefore(umAnoAtras);
    }
}
