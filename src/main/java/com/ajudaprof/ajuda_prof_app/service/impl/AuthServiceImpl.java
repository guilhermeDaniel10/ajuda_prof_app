package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Role;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.LoginRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.JWTAuthResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.ProfessorRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.RoleRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.security.JwtTokenProvider;
import com.ajudaprof.ajuda_prof_app.service.interfaces.AuthService;
import com.ajudaprof.ajuda_prof_app.service.interfaces.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    ProfessorService professorService;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> registerProfessor(@RequestBody ProfessorRequest professorDto) {
        // add check for username exists in a DB
        if (professorRepository.existsByUsername(professorDto.getUsername())) {
            throw new RepeatedResourceException("Professor", ExceptionValues.PROFESSOR_EXISTENTE_USERNAME.getValoresErro(), professorDto.getUsername());
        }

        // add check for email exists in DB
        if (professorRepository.existsByEmail(professorDto.getEmail())) {
            throw new RepeatedResourceException("Professor", ExceptionValues.PROFESSOR_EXISTENTE_EMAIL.getValoresErro(), professorDto.getEmail());
        }

        // create user object
        Professor user = new Professor();
        user.setUsername(professorDto.getUsername());
        user.setEmail(professorDto.getEmail());
        user.setPassword(passwordEncoder.encode(professorDto.getPassword()));
        user.setEscola(professorDto.getEscola());

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        professorRepository.save(user);

        return new ResponseEntity<>("Professor registered successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginRequest loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
}
