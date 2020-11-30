package alunos.alunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alunos.alunos.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Aluno findByNome(String nome);
}