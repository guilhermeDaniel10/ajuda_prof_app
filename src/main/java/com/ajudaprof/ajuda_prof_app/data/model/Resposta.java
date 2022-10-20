package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResposta;

    @ManyToOne
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;

    @Column
    private Double cotacaoResposta;

    @ManyToOne
    @JoinColumn(name="pergunta_id", nullable=false)
    private Pergunta pergunta;

    public Long getIdResposta() {
        return idResposta;
    }


    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Double getCotacaoResposta() {
        return cotacaoResposta;
    }

    public void setCotacaoResposta(Double cotacaoResposta) {
        this.cotacaoResposta = cotacaoResposta;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resposta)) return false;
        Resposta resposta = (Resposta) o;
        return getAluno().equals(resposta.getAluno()) && getCotacaoResposta().equals(resposta.getCotacaoResposta()) && getPergunta().equals(resposta.getPergunta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAluno(), getCotacaoResposta(), getPergunta());
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "idResposta=" + idResposta +
                ", aluno=" + aluno +
                ", cotacaoResposta=" + cotacaoResposta +
                ", pergunta=" + pergunta +
                '}';
    }
}
