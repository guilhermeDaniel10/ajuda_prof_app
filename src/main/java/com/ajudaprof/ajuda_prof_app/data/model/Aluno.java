package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAluno;
    @Column
    private String primeiroNome;
    @Column
    private String ultimoNome;
    @Column
    private String email;

    public Aluno() {

    }
    public Aluno(Long idAluno, String primeiroNome, String ultimoNome, String email) {
        this.idAluno = idAluno;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
    }

    public Aluno(Aluno a){
        this.idAluno = idAluno;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return idAluno.equals(aluno.idAluno) && primeiroNome.equals(aluno.primeiroNome) && ultimoNome.equals(aluno.ultimoNome) && email.equals(aluno.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAluno, primeiroNome, ultimoNome, email);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "idAluno=" + idAluno +
                ", primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
