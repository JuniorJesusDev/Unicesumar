package io.github.juniorjesustcc.repository;

import io.github.juniorjesustcc.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @EntityGraph(attributePaths = "perfis")
    Usuario findByEmail(String email);
}