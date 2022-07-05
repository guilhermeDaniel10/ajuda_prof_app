package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository  extends JpaRepository<Professor, Long> {

    Professor findByEmail(String email);
    Professor findByUsername(String username);
}
