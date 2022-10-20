package com.ajudaprof.ajuda_prof_app.data.model.builder;

import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;
import com.ajudaprof.ajuda_prof_app.data.model.Teste;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class TesteBuilder implements TesteBuilderInterface{

    //required
    private Turma turma;
    private String nome;

    //optional
    private Set<Pergunta> perguntas;
    private LocalDate dataTeste;

    public TesteBuilder(Turma turma, String nome) {
        this.turma = turma;
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
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

    public TesteBuilder setDataTeste(LocalDate dataTeste) {
        this.dataTeste = dataTeste;
        return this;
    }

    @Override
    public TesteBuilder setPerguntas(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;
        return this;
    }

    public Teste build(){
        return new Teste(this);
    }
}
