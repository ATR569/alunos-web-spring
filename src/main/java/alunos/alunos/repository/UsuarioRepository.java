package alunos.alunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import alunos.alunos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
