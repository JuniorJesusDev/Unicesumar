package io.github.juniorjesustcc.controller;

import io.github.juniorjesustcc.model.Usuario;
import io.github.juniorjesustcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Usuario usuario = usuarioService.findByEmail(username);
        if (usuario != null && passwordEncoder.matches(password, usuario.getSenha())) {
            // Autenticação bem-sucedida, redirecionar para a página principal
            return "redirect:/home";
        }
        // Autenticação falhou e volta para Login
        return "redirect:/login?error";
    }
}