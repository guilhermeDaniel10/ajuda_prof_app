package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.model.dto.TurmaDTO;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.AlunoRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.DefaultMessages;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.AlunoService;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addAluno(@RequestBody AlunoRequest aluno) {
        try {
            MessageResponse newAluno = alunoService.createAluno(aluno);
            return new ResponseEntity<>(newAluno, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists | ResourceNotFoundException | RepeatedResourceException exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Aluno>> getAllTurmas () {
        try {
            List<Aluno> alunos = alunoService.getAllAlunos();
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch( ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getAlunoById (@PathVariable("id") Long id) {
        try {
            Aluno aluno = alunoService.getAlunoById(id);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Object> getAlunoByTurma(@RequestParam String escola, @RequestParam Short ano, @RequestParam String sigla, @RequestParam Integer numero) {
        try {
            Aluno aluno = alunoService.getAlunoByTurmaNumero(new TurmaDTO(escola, ano, sigla), numero);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<Object> getAlunoByNome(@RequestParam String primeiroNome, @RequestParam String ultimoNome) {
        try {
            List<Aluno> alunos = alunoService.getAlunoByNome(primeiroNome, ultimoNome);
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Object> getAlunoByEmail(@RequestParam String email) {
        try {
            Aluno aluno = alunoService.getAlunoByEmail(email);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public Object updateAlunoByInfo(@RequestParam String escola, @RequestParam Short ano, @RequestParam String sigla, @RequestParam Integer numero, @RequestBody AlunoRequest aluno) {
        try {
            return alunoService.updateAluno(new TurmaDTO(escola, ano, sigla), numero, aluno);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public Object updateAlunoById(@PathVariable("id") Long id, @RequestBody AlunoRequest aluno) {
        try {
            return alunoService.updateAlunoById(id, aluno);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable("id") Long id) {
        try {
            alunoService.deleteAluno(id);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestParam String escola, @RequestParam Short ano, @RequestParam String sigla, @RequestParam Integer numero) {
        try {
            alunoService.deleteAlunoByNumeroTurma(new TurmaDTO(escola, ano, sigla), numero);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(),HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

}
