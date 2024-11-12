package io.github.juniorjesustcc.controller;

import io.github.juniorjesustcc.model.Endereco;
import io.github.juniorjesustcc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/novo_endereco")
    public String mostrarFormularioEndereco() {
        return "novo_endereco";
    }

    @PostMapping("/novo_endereco")
    public String novoEndereco(
            @RequestParam String rua,
            @RequestParam int numero,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String estado,
            @RequestParam String cep,
            @RequestParam String nomeSindico,
            @RequestParam String telefoneSindico,
            @RequestParam String ultimoAtendimento,
            Model model) {

        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        endereco.setNomeSindico(nomeSindico);
        endereco.setTelefoneSindico(telefoneSindico);
        endereco.setUltimoAtendimento(LocalDate.parse(ultimoAtendimento));

        enderecoRepository.save(endereco);

        model.addAttribute("message", "Endereço cadastrado com sucesso!");
        return "novo_endereco";
    }

    // Usando Get para buscar endereços
    @ResponseBody
    @GetMapping("/enderecos/buscar")
    public ResponseEntity<List<Endereco>> buscar(@RequestParam String q) {
        List<Endereco> enderecos = enderecoRepository.findByRuaContainingIgnoreCase(q);
        return ResponseEntity.ok(enderecos);
    }
}