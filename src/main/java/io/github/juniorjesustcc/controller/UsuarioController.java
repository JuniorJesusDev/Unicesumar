package io.github.juniorjesustcc.controller;

import io.github.juniorjesustcc.model.Usuario;
import io.github.juniorjesustcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario/criar")
    public String criarUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "criar_usuario";
    }

    @PostMapping("/usuario/criar")
    public String criarUsuario(@ModelAttribute Usuario usuario, @ModelAttribute("perfil") String perfilNome, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.salvarUsuario(usuario, perfilNome);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário criado com sucesso!");
            return "redirect:/usuario/criar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao criar usuário: " + e.getMessage());
            return "redirect:/usuario/criar";
        }
    }

    @GetMapping("/recuperacao-senha")
    public String mostrarPaginaRecuperacaoSenha() {
        return "esqueci_senha";
    }

    @GetMapping("/mainlogin")
    public String loginPage() {
        return "login";
    }
}

