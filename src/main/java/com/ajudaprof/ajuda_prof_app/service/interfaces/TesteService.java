package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.PerguntaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TesteRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;

import java.util.List;

public interface TesteService {

    MessageResponse createTeste(TesteRequest testeRequest);

    MessageResponse addPergunta(Long idTeste, PerguntaRequest perguntaRequest);

    void deleteTeste(Long idTeste);

    void deletePergunta(Long idTeste, PerguntaRequest perguntaRequest);

}
