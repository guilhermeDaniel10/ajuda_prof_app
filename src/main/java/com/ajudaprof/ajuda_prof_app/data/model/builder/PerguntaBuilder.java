package com.ajudaprof.ajuda_prof_app.data.model.builder;

import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;
import com.ajudaprof.ajuda_prof_app.data.model.Resposta;
import com.ajudaprof.ajuda_prof_app.data.model.Teste;

import java.util.Set;

public class PerguntaBuilder implements PerguntaBuilderInterface {

    //required
    private Teste teste;
    private String pergunta;
    private Double cotacao;

    //optional
    private Set<Resposta> respostas;

    public PerguntaBuilder(Teste teste, String pergunta, Double cotacao) {
        this.teste = teste;
        this.pergunta = pergunta;
        this.cotacao = cotacao;
    }

    @Override
    public PerguntaBuilder setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
        return this;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Double getCotacao() {
        return cotacao;
    }

    public void setCotacao(Double cotacao) {
        this.cotacao = cotacao;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public Pergunta build() {
        return new Pergunta(this);
    }
}
