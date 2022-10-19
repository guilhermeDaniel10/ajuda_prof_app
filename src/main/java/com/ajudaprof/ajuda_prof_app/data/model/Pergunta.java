package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPergunta;

    @ManyToOne
    @JoinColumn(name="teste_id", nullable = false)
    private Teste teste;

    @Column
    private String pergunta;

    @Column
    private Double cotacao;



    public Pergunta() {
    }

    public Pergunta(String pergunta, Double cotacao) {
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
}
