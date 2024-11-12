package io.github.juniorjesustcc.controller;

import io.github.juniorjesustcc.model.Endereco;
import io.github.juniorjesustcc.model.OrdemServico;
import io.github.juniorjesustcc.service.EnderecoService;
import io.github.juniorjesustcc.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.*;

@Controller
public class OrdemServicoController {

    private final EnderecoService enderecoService;
    private final OrdemServicoService ordemServicoService;

    @Autowired
    public OrdemServicoController(EnderecoService enderecoService, OrdemServicoService ordemServicoService) {
        this.enderecoService = enderecoService;
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping("/ordens_servico/criar")
    public String criarOrdemServicoForm(Model model) {
        List<Endereco> enderecos = enderecoService.buscarTodos();
        model.addAttribute("enderecos", enderecos);
        return "ordens_servico";
    }

    @PostMapping("/ordens_servico/criar")
    public String criarOrdemServico(@RequestParam("endereco") Long enderecoId,
                                    @RequestParam("data") String data,
                                    @RequestParam("observacao") String observacao,
                                    RedirectAttributes redirectAttributes) {
        try {
            Endereco endereco = enderecoService.buscarEnderecoPeloId(enderecoId);
            LocalDate dataFormatada;
            try {
                dataFormatada = parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Data inválida: " + data);
            }

            OrdemServico ordemServico = new OrdemServico();
            ordemServico.setEndereco(endereco);
            ordemServico.setData(dataFormatada);
            ordemServico.setObservacao(observacao);
            ordemServicoService.salvar(ordemServico);
            redirectAttributes.addFlashAttribute("mensagem", "Ordem de serviço criada com sucesso!");
            return "redirect:/ordens_servico";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao criar ordem de serviço: " + e.getMessage());
            return "redirect:/ordens_servico/criar";
        }
    }

    @PostMapping("/ordemServico/status/{id}")
    public String atualizarStatus(@PathVariable Long id,
                                  @RequestParam String status,
                                  @RequestParam String ultimoUsuario,
                                  RedirectAttributes redirectAttributes) {
        Optional<OrdemServico> ordemServicoOptional = ordemServicoService.findById(id);

        if (!ordemServicoOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar status: Ordem de Servico não encontrada");
            return "redirect:/atendimento";
        }

        OrdemServico ordemServico = ordemServicoOptional.get();
        ordemServico.setStatus(status);
        ordemServico.setUltimoUsuarioQueAlterouStatus(ultimoUsuario);

        // Quando o status for "Concluído", atualiza o ultimoAtendimento do Endereco com a data do dia
        if (status.equalsIgnoreCase("Concluído")) {
            Endereco endereco = ordemServico.getEndereco();
            endereco.setUltimoAtendimento(now());  // Atualiza a data de atendimento para a data atual
            enderecoService.salvar(endereco);  // Salva as alterações no Endereco
        }

        ordemServicoService.salvar(ordemServico);
        return "redirect:/atendimento";
    }

    @GetMapping("/ordens_servico")
    public String listarOrdens(Model model) {
        List<OrdemServico> ordens = ordemServicoService.listarOrdens();
        model.addAttribute("ordens", ordens);
        return "ordens_servico";
    }
}