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

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @CrossOrigin(origins = "http://localhost:4200")
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addMultiple")
    public ResponseEntity<MessageResponse> addMultipleAlunos(@RequestBody List<AlunoRequest> alunos) {
        try {
            MessageResponse newAluno = alunoService.createMultipleAlunos(alunos);
            return new ResponseEntity<>(newAluno, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists | ResourceNotFoundException | RepeatedResourceException exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<Aluno>> getAllTurmas() {
        try {
            List<Aluno> alunos = alunoService.getAllAlunos();
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getAlunoById(@PathVariable("id") Long id) {
        try {
            Aluno aluno = alunoService.getAlunoById(id);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/find")
    public ResponseEntity<Object> getAlunoByTurma(@RequestParam String usernameProf, @RequestParam Short ano, @RequestParam String sigla, @RequestParam Integer numero) {
        try {
            Aluno aluno = alunoService.getAlunoByTurmaNumero(new TurmaDTO(usernameProf, ano, sigla), numero);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findAllByTurma")
    public ResponseEntity<Object> getAlunosByTurma(@RequestParam String usernameProf, @RequestParam Short ano, @RequestParam String sigla) {
        try {
            List<Aluno> alunos = alunoService.getAllAlunosByTurma(new TurmaDTO(usernameProf, ano, sigla));
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findByNome")
    public ResponseEntity<Object> getAlunoByNome(@RequestParam String primeiroNome, @RequestParam String ultimoNome) {
        try {
            List<Aluno> alunos = alunoService.getAlunoByNome(primeiroNome, ultimoNome);
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findByEmail")
    public ResponseEntity<Object> getAlunoByEmail(@RequestParam String email) {
        try {
            Aluno aluno = alunoService.getAlunoByEmail(email);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (ResourceNotFoundException | RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public Object updateAlunoByInfo(@RequestParam String usernameProf, @RequestParam Short ano, @RequestParam String sigla, @RequestParam Integer numero, @RequestBody AlunoRequest aluno) {
        try {
            return alunoService.updateAluno(new TurmaDTO(usernameProf, ano, sigla), numero, aluno);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{id}")
    public Object updateAlunoById(@PathVariable("id") Long id, @RequestBody AlunoRequest aluno) {
        try {
            return alunoService.updateAlunoById(id, aluno);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        } catch (RepeatedResourceException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable("id") Long id) {
        try {
            alunoService.deleteAluno(id);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAlunoByInfo(@RequestParam String usernameProf, @RequestParam Short ano, @RequestParam String sigla, @RequestParam Integer numero) {
        try {
            alunoService.deleteAlunoByNumeroTurma(new TurmaDTO(usernameProf, ano, sigla), numero);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/boostrapAlunos")
    public ResponseEntity<?> bootstrapAlunos() {
        try {
            alunoService.boostrapAlunos();
            return new ResponseEntity<>(DefaultMessages.SUCESSO_BOOTSTRAP.getMessageAsResponse(), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }


}
