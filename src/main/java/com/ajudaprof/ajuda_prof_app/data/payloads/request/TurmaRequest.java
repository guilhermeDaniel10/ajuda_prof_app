package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TurmaRequest {
    @NotBlank
    @NotNull
    private String escola;
    @NotBlank
    @NotNull
    private Short ano;
    @NotBlank
    @NotNull
    private String sigla;

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
}
