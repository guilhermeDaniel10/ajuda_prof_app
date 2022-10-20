package com.ajudaprof.ajuda_prof_app.data.model.builder;

import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public interface TesteBuilderInterface {

    TesteBuilder setPerguntas(Set<Pergunta> perguntas);

    TesteBuilder setDataTeste(LocalDate dataTeste);
}
