package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByTurmaAndNumeroAluno(Turma turma, Integer numeroAluno);

    Aluno findByEmail(String email);

    List<Aluno> findByPrimeiroNomeAndUltimoNome(String primeiroNome, String ultimoNome);

    List<Aluno> findByTurma(Turma turma);
}
