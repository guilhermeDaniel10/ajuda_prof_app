package com.ajudaprof.ajuda_prof_app.data.model;

import com.ajudaprof.ajuda_prof_app.data.model.builder.TesteBuilder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTeste;

    @Column
    private String nome;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonDeserialize(as = LocalDate.class)
    private LocalDate dataTeste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @OneToMany(mappedBy = "teste", cascade = CascadeType.ALL)
    private Set<Pergunta> perguntas;

    public Long getIdTeste() {
        return idTeste;
    }

    public Teste(TesteBuilder builder) {
        this.turma = builder.getTurma();
        this.perguntas = builder.getPerguntas();
        this.nome = builder.getNome();
        this.dataTeste = builder.getDataTeste();
    }

    public Teste(Turma turma) {
        this.turma = turma;
    }

    public Teste() {
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

    public void setDataTeste(LocalDate dataTeste) {
        this.dataTeste = dataTeste;
    }

    public boolean addPergunta(Pergunta pergunta) {
        if (pergunta != null && !this.perguntas.contains(pergunta)) {
            this.perguntas.add(pergunta);
            return true;
        }
        return false;
    }

    public boolean removePergunta(Pergunta pergunta) {
        if (pergunta != null && this.perguntas.contains(pergunta)) {
            this.perguntas.remove(pergunta);
            return true;
        }
        return false;
    }


    public void setPerguntas(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teste)) return false;
        Teste teste = (Teste) o;
        return getNome().equals(teste.getNome()) && Objects.equals(getDataTeste(), teste.getDataTeste()) && getTurma().equals(teste.getTurma()) && Objects.equals(getPerguntas(), teste.getPerguntas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDataTeste(), getTurma(), getPerguntas());
    }
}
