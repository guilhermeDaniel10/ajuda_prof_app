package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;
import com.ajudaprof.ajuda_prof_app.data.model.Teste;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.model.builder.PerguntaBuilder;
import com.ajudaprof.ajuda_prof_app.data.model.builder.TesteBuilder;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.PerguntaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TesteRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.PerguntaRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.TesteRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.TurmaRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TesteService;
import com.ajudaprof.ajuda_prof_app.utils.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TesteServiceImpl implements TesteService {

    @Autowired
    TesteRepository testeRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Override
    public MessageResponse createTeste(TesteRequest testeRequest) {
        Turma turma;

        try {
            turma = turmaRepository.findByIdTurma(testeRequest.getIdTurma());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {String.valueOf(testeRequest.getIdTurma())};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_ID_INEXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        Teste teste = this.testeRepository.findByNomeAndTurma(testeRequest.getNome(), turma);
        if (teste != null) {
            String[] arrObj = {testeRequest.getNome()};
            throw new RepeatedResourceException("Teste", ExceptionValues.TESTE_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } else {
            Teste newTeste = new TesteBuilder(turma, testeRequest.getNome()).setDataTeste(testeRequest.getDataTeste()).build();

            Teste testeCriado = testeRepository.save(newTeste);

            Set<Pergunta> perguntas = new HashSet<>();
            for (PerguntaRequest pR : testeRequest.getPerguntas()) {
                Pergunta p = new PerguntaBuilder(testeCriado, pR.getPergunta(), pR.getCotacao()).build();
                perguntaRepository.save(p);
            }
            return new MessageResponse("Novo teste adicionado");
        }
    }
}
