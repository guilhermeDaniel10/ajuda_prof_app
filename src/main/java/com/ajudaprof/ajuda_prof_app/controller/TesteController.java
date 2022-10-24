package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.PerguntaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TesteRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.DefaultMessages;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/teste")
public class TesteController {

    @Autowired
    TesteService testeService;

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTeste(@RequestBody TesteRequest teste) {
        try {
            MessageResponse newProfessor = testeService.createTeste(teste);
            return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists | ResourceNotFoundException | RepeatedResourceException exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addPergunta/{id}")
    public ResponseEntity<MessageResponse> addPergunta(@PathVariable Long id, @RequestBody PerguntaRequest perguntaRequest) {
        try {
            MessageResponse newProfessor = testeService.addPergunta(id, perguntaRequest);
            return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists | ResourceNotFoundException | RepeatedResourceException exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeste(@PathVariable("id") Long id) {
        try {
            testeService.deleteTeste(id);
            return new ResponseEntity<>(DefaultMessages.SUCESSO_APAGADO.getMessageAsResponse(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            MessageResponse erro = new MessageResponse(ex.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }
}
