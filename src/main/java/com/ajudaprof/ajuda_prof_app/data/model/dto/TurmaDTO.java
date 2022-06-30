package com.ajudaprof.ajuda_prof_app.data.model.dto;

import java.util.Objects;

public class TurmaDTO {

    private String escola;
    private Short ano;
    private String sigla;

    public TurmaDTO(String escola, Short ano, String sigla) {
        this.escola = escola;
        this.ano = ano;
        this.sigla = sigla;
    }

    public TurmaDTO(TurmaDTO turmaDTO){
        this.escola = turmaDTO.escola;
        this.ano = turmaDTO.ano;
        this.sigla = turmaDTO.sigla;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurmaDTO turmaDTO = (TurmaDTO) o;
        return escola.equals(turmaDTO.escola) && ano.equals(turmaDTO.ano) && sigla.equals(turmaDTO.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escola, ano, sigla);
    }

    @Override
    public String toString() {
        return "TurmaDTO{" +
                "escola='" + escola + '\'' +
                ", ano=" + ano +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    public String toStringDTO(){
        return this.getEscola() + ", " + this.ano + "º" + this.sigla;
    }
}
