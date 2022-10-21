package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Role;
import com.ajudaprof.ajuda_prof_app.data.model.dto.TurmaDTO;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.AlunoRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.LoginRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.DefaultMessages;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.JWTAuthResponse;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.ProfessorRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.RoleRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.security.JwtTokenProvider;
import com.ajudaprof.ajuda_prof_app.service.interfaces.AuthService;
import com.ajudaprof.ajuda_prof_app.service.interfaces.ProfessorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AuthService authService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addProfessor(@RequestBody ProfessorRequest professor) {
        try {
            MessageResponse newProfessor = professorService.createProfessor(professor);
            return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists | ResourceNotFoundException | RepeatedResourceException  exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "REST API to Signin or Login Professor")
    @PostMapping("/signup")
    public ResponseEntity<?> registerProfessor(@RequestBody ProfessorRequest professorDto){
        try {
            return authService.registerProfessor(professorDto);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginDto){
        try {
            return authService.authenticateUser(loginDto);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/email")
    public Object updateProfessorByEmail(@RequestParam String email, @RequestBody ProfessorRequest professorRequest) {
        try {
            return professorService.updateProfessorByEmail(email, professorRequest);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/username")
    public Object updateProfessorByUsername(@RequestParam String username, @RequestBody ProfessorRequest professorRequest) {
        try {
            return professorService.updateProfessorByUsername(username, professorRequest);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/id")
    public Object updateProfessorByUsername(@RequestParam Long id, @RequestBody ProfessorRequest professorRequest) {
        try {
            return professorService.updateProfessorById(id, professorRequest);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<Object> getProfessorById(@RequestParam Long id) {
        try {
            Professor professor = professorService.getProfessorById(id);
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Object> getProfessorByEmail(@RequestParam String email) {
        try {
            Professor professor = professorService.getProfessorByEmail(email);
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<Object> getProfessorByUsername(@RequestParam String username) {
        try {
            Professor professor = professorService.getProfessorByUsername(username);
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfessorById(@PathVariable("id") Long id) {
        try {
            professorService.deleteProfessorById(id);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteByUsername")
    public ResponseEntity<Object> deleteProfessorByUsername(@RequestParam String username) {
        try {
            professorService.deleteProfessorByUsername(username);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteByEmail")
    public ResponseEntity<Object> deleteProfessorByEmail(@RequestParam String email) {
        try {
            professorService.deleteProfessorByEmail(email);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }


}
