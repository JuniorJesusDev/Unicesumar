package io.github.juniorjesustcc.service;

import io.github.juniorjesustcc.model.Perfil;
import io.github.juniorjesustcc.model.Usuario;
import io.github.juniorjesustcc.repository.PerfilRepository;
import io.github.juniorjesustcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario salvarUsuario(Usuario usuario, String perfilNome) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Verificar se o perfil já existe no banco de dados
        Perfil perfil = perfilRepository.findByNome(perfilNome);
        if (perfil == null) {
            perfil = new Perfil();
            perfil.setNome(perfilNome);
            perfil = perfilRepository.save(perfil);
        }

        Set<Perfil> perfis = usuario.getPerfis() != null ? usuario.getPerfis() : new HashSet<>();
        if (!perfis.contains(perfil)) {
            perfis.add(perfil);
        }
        usuario.setPerfis(perfis);

        return usuarioRepository.save(usuario);
    }
}

