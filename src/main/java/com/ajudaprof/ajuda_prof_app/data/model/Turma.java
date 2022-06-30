package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTurma;
    @Column
    private String escola;
    @Column
    private Short ano;
    @Column
    private String sigla;


    public Turma(String escola, Integer idTurma, Short ano, String sigla) {
        this.escola = escola;
        this.idTurma = idTurma;
        this.ano = ano;
        this.sigla = sigla;
    }

    public Turma() {
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return escola.equals(turma.escola) && ano.equals(turma.ano) && sigla.equals(turma.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escola, ano, sigla);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "idTurma=" + idTurma +
                ", escola='" + escola + '\'' +
                ", ano=" + ano +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
