package br.com.fiap.alunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.alunos.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
