package com.ajudaprof.ajuda_prof_app.controller;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.PerguntaWithRespostaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.service.interfaces.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/resposta")
public class RespostaController {

    @Autowired
    RespostaService respostaService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addOrUpdateRespostas")
    public ResponseEntity<MessageResponse> addOrUpdateRespostas(@RequestBody List<PerguntaWithRespostaRequest> respostas) {
        try {
            MessageResponse newRespostas = respostaService.addOrUpdateRespostas(respostas);
            return new ResponseEntity<>(newRespostas, HttpStatus.CREATED);
        } catch (ResourceAlreadyExists exception) {
            MessageResponse erro = new MessageResponse(exception.getMessage());
            return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
        }
    }
}
