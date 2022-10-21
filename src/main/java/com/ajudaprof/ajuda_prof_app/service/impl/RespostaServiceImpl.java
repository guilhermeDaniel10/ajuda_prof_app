package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;
import com.ajudaprof.ajuda_prof_app.data.model.Resposta;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.PerguntaWithRespostaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.RespostaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.AlunoRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.PerguntaRepository;
import com.ajudaprof.ajuda_prof_app.data.repository.RespostaRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.RespostaService;
import com.ajudaprof.ajuda_prof_app.utils.StringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RespostaServiceImpl implements RespostaService {
    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public MessageResponse addOrUpdateRespostas(List<PerguntaWithRespostaRequest> respostasRequest) {

        for (PerguntaWithRespostaRequest pRR : respostasRequest) {
            Pergunta perguntaAtual;
            Long idPergunta = pRR.getIdPergunta();
            try {
                perguntaAtual = perguntaRepository.findById(idPergunta).get();
                if (perguntaAtual == null) {
                    throw new ResourceNotFoundException("Pergunta", ExceptionValues.PERGUNTA_REPETIDA.getValoresErro(), idPergunta);
                }
            } catch (NoSuchElementException ex) {
                throw new ResourceNotFoundException("Pergunta", ExceptionValues.PERGUNTA_REPETIDA.getValoresErro(), idPergunta);
            }

            for (RespostaRequest rR : pRR.getRespostas()) {
                Aluno alunoAtual;
                Long idAluno = rR.getIdAluno();
                try {
                    alunoAtual = alunoRepository.findById(idAluno).get();
                    if (alunoAtual == null) {
                        throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_INEXISTENTE_ID.getValoresErro(), idAluno);
                    }
                } catch (NoSuchElementException ex) {
                    throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_INEXISTENTE_ID.getValoresErro(), idAluno);
                }
                Resposta respostaAtual;
                try {
                    respostaAtual = respostaRepository.findByAlunoAndPergunta(alunoAtual, perguntaAtual);
                    if (respostaAtual == null) {
                        Resposta novaResposta = new Resposta(alunoAtual, rR.getCotacaoResposta(), perguntaAtual);
                        respostaRepository.save(novaResposta);
                    } else {
                        respostaAtual.setCotacaoResposta(rR.getCotacaoResposta());
                        respostaRepository.save(respostaAtual);
                    }
                } catch (NoSuchElementException | ResourceNotFoundException ex) {
                    Resposta novaResposta = new Resposta(alunoAtual, rR.getCotacaoResposta(), perguntaAtual);
                    respostaRepository.save(novaResposta);
                }

            }


        }


        return new MessageResponse("Respostas adicionadas/atualizadas");
    }
}
