package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-heroku")
public class CompileController {

    @GetMapping("/hello")
    public ResponseEntity<Object> testDeploy() {
        try {
            String hello = "Hello World";
            return new ResponseEntity<>(hello, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }
}
