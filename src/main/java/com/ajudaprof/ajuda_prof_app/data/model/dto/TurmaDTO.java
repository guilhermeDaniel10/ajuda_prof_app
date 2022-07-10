package com.ajudaprof.ajuda_prof_app.data.model.dto;

import java.util.Objects;

public class TurmaDTO {

    private String usernameProfessor;
    private Short ano;
    private String sigla;

    public TurmaDTO(String usernameProfessor, Short ano, String sigla) {
        this.usernameProfessor = usernameProfessor;
        this.ano = ano;
        this.sigla = sigla;
    }

    public TurmaDTO(TurmaDTO turmaDTO){
        this.usernameProfessor = turmaDTO.usernameProfessor;
        this.ano = turmaDTO.ano;
        this.sigla = turmaDTO.sigla;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurmaDTO turmaDTO = (TurmaDTO) o;
        return usernameProfessor.equals(turmaDTO.usernameProfessor) && ano.equals(turmaDTO.ano) && sigla.equals(turmaDTO.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernameProfessor, ano, sigla);
    }

    @Override
    public String toString() {
        return "TurmaDTO{" +
                "username Professor='" + usernameProfessor + '\'' +
                ", ano=" + ano +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    public String toStringDTO(){
        return this.getUsernameProfessor() + ", " + this.ano + "º" + this.sigla;
    }
}
