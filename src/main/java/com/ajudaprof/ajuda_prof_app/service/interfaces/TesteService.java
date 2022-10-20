package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TesteRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;

public interface TesteService {

    MessageResponse createTeste(TesteRequest testeRequest);
}
