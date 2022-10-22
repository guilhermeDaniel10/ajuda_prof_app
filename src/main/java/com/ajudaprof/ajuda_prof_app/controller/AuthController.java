package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.LoginRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.ProfessorRepository;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

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
}
