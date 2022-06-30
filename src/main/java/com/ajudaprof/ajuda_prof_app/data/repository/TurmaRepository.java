package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {

    Turma findByEscolaAndAnoAndSigla(String escola, Short ano, String sigla);
}
