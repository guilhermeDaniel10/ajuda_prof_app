package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.model.dto.TurmaDTO;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlunoRequest {
    @NotBlank
    @NotNull
    private String primeiroNome;
    @NotBlank
    @NotNull
    private String ultimoNome;
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String escola;
    @NotBlank
    @NotNull
    private Short ano;
    @NotBlank
    @NotNull
    private String sigla;
    @NotBlank
    @NotNull
    private Integer numeroAluno;

    public String getPrimeiroNome() {
        return this.primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return this.ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
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
        return this.numeroAluno;
    }

    public void setNumeroAluno(Integer numeroAluno) {
        this.numeroAluno = numeroAluno;
    }
}
