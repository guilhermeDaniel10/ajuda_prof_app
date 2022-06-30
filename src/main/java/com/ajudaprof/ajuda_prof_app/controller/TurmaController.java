package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.DefaultMessages;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/turma")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTurma(@RequestBody TurmaRequest turma) {
        try {
            MessageResponse newTurma = turmaService.createTurma(turma);
            return new ResponseEntity<>(newTurma, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public Object updateTurma(@PathVariable Integer id, @RequestBody TurmaRequest turma) {
        try {
            return turmaService.updateTurma(id, turma);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public Object updateTurmaByInfo(@RequestParam String escola, @RequestParam Short ano, @RequestParam String sigla, @RequestBody TurmaRequest turma) {
        try {
            return turmaService.updateTurmaByInfo(escola, ano, sigla, turma);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        try {
            turmaService.deleteTurma(id);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestParam String escola, @RequestParam Short ano, @RequestParam String sigla) {
        try {
            turmaService.deleteTurmaByInfo(escola, ano, sigla);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

}