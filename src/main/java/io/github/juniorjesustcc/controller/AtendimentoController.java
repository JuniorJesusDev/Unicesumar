package io.github.juniorjesustcc.controller;

import io.github.juniorjesustcc.model.Endereco;
import io.github.juniorjesustcc.model.OrdemServico;
import io.github.juniorjesustcc.service.EnderecoService;
import io.github.juniorjesustcc.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Controller
public class AtendimentoController {

    private final OrdemServicoService ordemServicoService;

    @Autowired
    public AtendimentoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping("/atendimento")
    public String listarAtendimentos(Model model,
                                     @RequestParam(required = false) String status,
                                     @AuthenticationPrincipal UserDetails currentUser) {
        List<OrdemServico> ordens;
        if (status != null && !status.isEmpty()) {
            ordens = ordemServicoService.filterByStatus(status);
        } else {
            LocalDate hoje = LocalDate.now();
            ordens = ordemServicoService.listarOrdensDoDia(hoje);
        }
        model.addAttribute("ordens", ordens);
        model.addAttribute("usuarioLogado", currentUser.getUsername());
        return "rota_atendimento";
    }

    @PutMapping("/atendimento/{id}")
    public String atualizaStatusOrdemServico(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Optional<OrdemServico> ordemServicoOpt = ordemServicoService.findById(id);
        if (ordemServicoOpt.isPresent()) {
            OrdemServico ordemServico = ordemServicoOpt.get();
            ordemServicoService.atualizarStatus(ordemServico, status);
        }
        return "redirect:/atendimento";
    }

    @GetMapping("/relatorios")
    public String listarRelatorios(Model model,
                                   @RequestParam(required = false) String status,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                   @AuthenticationPrincipal UserDetails currentUser) {
        List<OrdemServico> ordens;
        ordens = ordemServicoService.filterByDateAndStatus(data, status);
        model.addAttribute("ordens", ordens);
        model.addAttribute("usuarioLogado", currentUser.getUsername());
        return "relatorios";
    }

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/atendimentos-pendentes")
    public String getEnderecosPendentes(Model model) {
        List<Endereco> enderecosPendentes = enderecoService.buscarEnderecosPendentes();
        model.addAttribute("enderecos", enderecosPendentes);
        return "gerar_preventivas";
    }
}


