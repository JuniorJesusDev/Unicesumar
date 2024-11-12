package io.github.juniorjesustcc.controller;

import io.github.juniorjesustcc.model.Usuario;
import io.github.juniorjesustcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    } // Redireciona para home

    @GetMapping("/test") //Apenas um teste para ver a comunicação com o BD
    @ResponseBody
    public String testDb() {
        Usuario usuario = usuarioService.findByEmail("teste@teste.com");
        if (usuario != null) {
            return "Usuário encontrado: " + usuario.getEmail() + " Comunicação com o BD esta efetuada com sucesso"; //Lembrando que criei um usuario teste@teste.com
        } else {
            return "Usuário não encontrado";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        // Redireciona para a página de login após o logout
        return "redirect:/login";
    }
}
