package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Teste;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesteRepository extends JpaRepository<Teste, Long> {

    Teste findByNomeAndTurma(String nome, Turma turma);
}
