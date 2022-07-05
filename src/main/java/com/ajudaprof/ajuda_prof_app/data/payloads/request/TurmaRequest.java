package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TurmaRequest {

    @NotBlank
    @NotNull
    private String usernameProfessor;
    @NotBlank
    @NotNull
    private Short ano;
    @NotBlank
    @NotNull
    private String sigla;

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
}
