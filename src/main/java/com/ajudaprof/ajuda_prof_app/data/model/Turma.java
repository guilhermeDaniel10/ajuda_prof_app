package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTurma;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="professor_id", nullable=false)
    private Professor professor;
    @Column
    private Short ano;
    @Column
    private String sigla;

    @OneToMany(mappedBy = "turma",cascade = CascadeType.ALL)
    private Set<Teste> testes;

    @OneToMany(mappedBy = "turma",cascade = CascadeType.ALL)
    private Set<Aluno> alunos;


    public Turma(Professor professor, Short ano, String sigla) {
        this.professor = professor;
        this.ano = ano;
        this.sigla = sigla;
    }

    public Turma() {
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Integer getIdTurma() {
        return this.idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String turmaFormat(){
        return this.ano + "º" + this.sigla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return professor.equals(turma.professor) && ano.equals(turma.ano) && sigla.equals(turma.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professor, ano, sigla);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "professor=" + professor +
                ", ano=" + ano +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
