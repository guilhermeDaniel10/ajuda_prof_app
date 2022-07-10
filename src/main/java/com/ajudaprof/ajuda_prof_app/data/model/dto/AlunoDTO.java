package com.ajudaprof.ajuda_prof_app.data.model.dto;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;

import javax.persistence.*;
import java.util.Objects;

public class AlunoDTO {


    private String primeiroNome;
    private String ultimoNome;
    private String email;
    private String usernameProfessor;
    private Short ano;
    private String sigla;
    private Integer numeroAluno;

    public AlunoDTO() {

    }

    public AlunoDTO(String primeiroNome, String ultimoNome, String email, String usernameProfessor, Short ano, String sigla, Integer numeroAluno) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.usernameProfessor = usernameProfessor;
        this.ano = ano;
        this.sigla = sigla;
        this.numeroAluno = numeroAluno;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsernameProfessor() {
        return usernameProfessor;
    }

    public void setUsernameProfessor(String usernameProfessor) {
        this.usernameProfessor = usernameProfessor;
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

    public Integer getNumeroAluno() {
        return numeroAluno;
    }

    public void setNumeroAluno(Integer numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoDTO alunoDTO = (AlunoDTO) o;
        return primeiroNome.equals(alunoDTO.primeiroNome) && ultimoNome.equals(alunoDTO.ultimoNome) && email.equals(alunoDTO.email) && usernameProfessor.equals(alunoDTO.usernameProfessor) && ano.equals(alunoDTO.ano) && sigla.equals(alunoDTO.sigla) && numeroAluno.equals(alunoDTO.numeroAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primeiroNome, ultimoNome, email, usernameProfessor, ano, sigla, numeroAluno);
    }

    @Override
    public String toString() {
        return "AlunoDTO{" +
                "primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", email='" + email + '\'' +
                ", usernameProfessor='" + usernameProfessor + '\'' +
                ", ano=" + ano +
                ", sigla='" + sigla + '\'' +
                ", numeroAluno=" + numeroAluno +
                '}';
    }
}
