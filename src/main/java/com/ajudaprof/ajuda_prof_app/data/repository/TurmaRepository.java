package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    Turma findByProfessorAndAnoAndSigla(Professor professor, Short ano, String sigla);
    List<Turma> findByProfessor(Professor professor);

    Turma findByIdTurma(Integer idTurma);

}
