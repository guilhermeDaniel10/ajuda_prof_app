package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Role;
import com.ajudaprof.ajuda_prof_app.data.model.Teste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
