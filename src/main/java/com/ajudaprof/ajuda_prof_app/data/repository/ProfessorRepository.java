package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository  extends JpaRepository<Professor, Long> {

    Professor findByEmail(String email);
    Professor findByUsername(String username);
    Optional<Professor> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
