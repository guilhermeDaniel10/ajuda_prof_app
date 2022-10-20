package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TesteRequest {
    @NotBlank
    @NotNull
    private Integer idTurma;

    @NotBlank
    @NotNull
    private String nome;


    private LocalDate dataTeste;

    @NotBlank
    @NotNull
    private List<PerguntaRequest> perguntas;

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public List<PerguntaRequest> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntaRequest> perguntas) {
        this.perguntas = perguntas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataTeste() {
        return dataTeste;
    }

    public void setDataTeste(LocalDate dataTeste) {
        this.dataTeste = dataTeste;
    }
}
