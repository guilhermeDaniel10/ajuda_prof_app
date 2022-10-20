package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.RespostaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;

import java.util.List;

public interface RespostaService {

    MessageResponse addRespostas(Long idPergunta, List<RespostaRequest> respostasRequest);
}
