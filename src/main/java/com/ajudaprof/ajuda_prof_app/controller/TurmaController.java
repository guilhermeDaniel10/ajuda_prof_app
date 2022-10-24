package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.DefaultMessages;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/turma")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    //@CrossOrigin(origins = "http://localhost:4200")
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
    public Object updateTurmaByInfo(@RequestParam String username, @RequestParam Short ano, @RequestParam String sigla, @RequestBody TurmaRequest turma) {
        try {
            return turmaService.updateTurmaByInfo(username, ano, sigla, turma);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTurma(@PathVariable("id") Integer id) {
        try {
            turmaService.deleteTurma(id);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTurmaByInfo(@RequestParam String username, @RequestParam Short ano, @RequestParam String sigla) {
        try {
            turmaService.deleteTurmaByInfo(username, ano, sigla);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<Turma>> getAllTurmas () {
        try {
            List<Turma> turmas = turmaService.getAllTurma();
            return new ResponseEntity<>(turmas, HttpStatus.OK);
        } catch( ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getTurmaById (@PathVariable("id") Integer id) {
        try {
            Turma turma = turmaService.getASingleTurma(id);
            return new ResponseEntity<>(turma, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Object> getTurmaByInfo(@RequestParam String username, @RequestParam Short ano, @RequestParam String sigla) {
        try {
            Turma turma = turmaService.getTurmaByInfo(username, ano, sigla);
            return new ResponseEntity<>(turma, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByProfessor")
    public ResponseEntity<Object> getTurmaByProfessor(@RequestParam String username) {
        try {
            List<Turma> turma = turmaService.getTurmaByProfessor(username);
            return new ResponseEntity<>(turma, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

}
