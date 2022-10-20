package com.ajudaprof.ajuda_prof_app.data.model;

import com.ajudaprof.ajuda_prof_app.data.model.builder.PerguntaBuilder;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPergunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teste_id", nullable = false)
    private Teste teste;

    @Column
    private String pergunta;

    @Column
    private Double cotacao;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL)
    private Set<Resposta> respostas;


    public Pergunta() {
    }

    public Pergunta(PerguntaBuilder builder){
        this.teste = builder.getTeste();
        this.pergunta = builder.getPergunta();
        this.cotacao = builder.getCotacao();
        this.respostas = builder.getRespostas();
    }

    public Pergunta(Teste teste, String pergunta, Double cotacao) {
        this.teste = teste;
        this.pergunta = pergunta;
        this.cotacao = cotacao;
    }

    public Long getIdPergunta() {
        return idPergunta;
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

    public boolean addResposta(Resposta resposta) {
        if (resposta != null && !this.respostas.contains(resposta)) {
            this.respostas.add(resposta);
            return true;
        }
        return false;
    }

    public boolean removeResposta(Resposta resposta) {
        if (resposta != null && this.respostas.contains(resposta)) {
            this.respostas.remove(resposta);
            return true;
        }
        return false;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pergunta)) return false;
        Pergunta pergunta1 = (Pergunta) o;
        return getTeste().equals(pergunta1.getTeste()) && getPergunta().equals(pergunta1.getPergunta()) && getCotacao().equals(pergunta1.getCotacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPergunta(), getCotacao());
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "idPergunta=" + idPergunta +
                ", teste=" + teste +
                ", pergunta='" + pergunta + '\'' +
                ", cotacao=" + cotacao +
                ", respostas=" + respostas +
                '}';
    }
}
