package com.ajudaprof.ajuda_prof_app.data.model.builder;

import com.ajudaprof.ajuda_prof_app.data.model.Resposta;

import java.util.Set;

public interface PerguntaBuilderInterface {

    PerguntaBuilder setRespostas(Set<Resposta> respostas);
}
