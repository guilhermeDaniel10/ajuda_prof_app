package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Role;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.LoginRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.JWTAuthResponse;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.ProfessorRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.RoleRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.security.JwtTokenProvider;
import com.ajudaprof.ajuda_prof_app.service.interfaces.ProfessorService;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import com.ajudaprof.ajuda_prof_app.utils.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public MessageResponse createProfessor(ProfessorRequest professorRequest) {
        Professor newProfessor = new Professor();
        newProfessor.setUsername(professorRequest.getUsername());
        newProfessor.setEmail(professorRequest.getEmail());
        newProfessor.setEscola(professorRequest.getEscola());
        newProfessor.setPassword(professorRequest.getPassword());
        try {
            professorRepository.save(newProfessor);
        } catch (DataAccessException e) {
            throw new RepeatedResourceException("Professor", ExceptionValues.PROFESSOR_EXISTENTE.getValoresErro(), professorRequest.getUsername());
        }
        return new MessageResponse("Novo professor adicionado");
    }

    /*@Override
    public MessageResponse registarProfessor(ProfessorRequest professorRequest) {


    }

    @Override
    public ResponseEntity<JWTAuthResponse> authenticateUser(LoginRequest loginDto) {

    }*/


    @Override
    public Professor updateProfessorByEmail(String email, ProfessorRequest professorRequest) {
        Professor professor;
        try {
            professor = this.getProfessorByEmail(email);

        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {email};
            throw new ResourceNotFoundException("Professor", ExceptionValues.PROFESSOR_INEXISTENTE_EMAIL.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        professor.setEmail(professorRequest.getEmail());
        professor.setEscola(professorRequest.getEscola());
        professor.setPassword(professorRequest.getPassword());
        professor.setUsername(professorRequest.getUsername());
        try {
            professorRepository.save(professor);
        } catch (DataAccessException e) {
            throw new RepeatedResourceException("Professor", ExceptionValues.PROFESSOR_EXISTENTE_ALL_INFO.getValoresErro(), professorRequest.toString());
        }

        return professor;
    }

    @Override
    public Professor updateProfessorByUsername(String username, ProfessorRequest professorRequest) {
        Professor professor;
        try {
            professor = this.getProfessorByUsername(username);

        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {username};
            throw new ResourceNotFoundException("Professor", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        professor.setEmail(professorRequest.getEmail());
        professor.setEscola(professorRequest.getEscola());
        professor.setPassword(professorRequest.getPassword());
        professor.setUsername(professorRequest.getUsername());

        try {
            professorRepository.save(professor);
        } catch (DataAccessException e) {
            throw new RepeatedResourceException("Professor", ExceptionValues.PROFESSOR_EXISTENTE.getValoresErro(), professorRequest.getUsername());
        }

        return professor;
    }

    @Override
    public Professor updateProfessorById(Long idProfessor, ProfessorRequest professorRequest) {
        Professor professor;
        try {
            professor = this.getProfessorById(idProfessor);

        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Professor", "Id", idProfessor);
        }
        professor.setEmail(professorRequest.getEmail());
        professor.setEscola(professorRequest.getEscola());
        professor.setPassword(professorRequest.getPassword());
        professor.setUsername(professorRequest.getUsername());
        try {
            professorRepository.save(professor);
        } catch (DataAccessException e) {
            throw new RepeatedResourceException("Professor", ExceptionValues.PROFESSOR_EXISTENTE.getValoresErro(), professorRequest.getUsername());
        }

        return professor;
    }

    @Override
    public Professor getProfessorByUsername(String username) {
        Professor professor = professorRepository.findByUsername(username);
        if (professor == null) {
            String[] arrObj = {username};
            throw new ResourceNotFoundException("Professor", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return professor;
    }

    @Override
    public Professor getProfessorById(Long idProfessor) {
        return professorRepository.findById(idProfessor).orElseThrow(() -> new ResourceNotFoundException("Professor", "id", idProfessor));
    }

    @Override
    public Professor getProfessorByEmail(String email) {
        Professor professor = professorRepository.findByEmail(email);
        if (professor == null) {
            String[] arrObj = {email};
            throw new ResourceNotFoundException("Professor", ExceptionValues.PROFESSOR_INEXISTENTE_EMAIL.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return professor;
    }

    @Override
    public void deleteProfessorById(Long idProfessor) {
        Professor professor = this.getProfessorById(idProfessor);
        if (professor == null) {
            throw new ResourceNotFoundException("Professor", "Id", idProfessor);
        }
        professorRepository.delete(professor);
    }

    @Override
    public void deleteProfessorByUsername(String username) {
        Professor professor = this.getProfessorByUsername(username);
        if (professor == null) {
            String[] arrObj = {professor.getUsername()};
            throw new ResourceNotFoundException("Professor", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        professorRepository.delete(professor);
    }

    @Override
    public void deleteProfessorByEmail(String email) {
        Professor professor = this.getProfessorByEmail(email);
        if (professor == null) {
            String[] arrObj = {professor.getUsername()};
            throw new ResourceNotFoundException("Professor", ExceptionValues.PROFESSOR_INEXISTENTE_EMAIL.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        professorRepository.delete(professor);
    }

    @Override
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Professor professor = professorRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Professor não encontrado com username ou email:" + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(professor.getEmail(), professor.getPassword(), mapRolesToAuthorities(professor.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
