package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;
    @Column
    private Integer numeroAluno;

    @OneToMany(mappedBy="aluno", cascade= CascadeType.ALL)
    private Set<Resposta> respostas;

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Aluno() {

    }

    public Aluno(Long idAluno, String primeiroNome, String ultimoNome, String email, Integer numeroAluno) {
        this.idAluno = idAluno;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.numeroAluno = numeroAluno;
    }

    public Aluno(Aluno a) {
        this.idAluno = a.idAluno;
        this.primeiroNome = a.primeiroNome;
        this.ultimoNome = a.ultimoNome;
        this.email = a.email;
        this.numeroAluno = a.numeroAluno;
    }

    public Long getIdAluno() {
        return this.idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

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

    public Turma getTurma() {
        return turma;
    }
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumeroAluno() {
        return this.numeroAluno;
    }

    public void setNumeroAluno(Integer numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return primeiroNome.equals(aluno.primeiroNome) && ultimoNome.equals(aluno.ultimoNome) && email.equals(aluno.email) && turma.equals(aluno.turma) && numeroAluno.equals(aluno.numeroAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primeiroNome, ultimoNome, email, turma, numeroAluno);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "idAluno=" + idAluno +
                ", primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", email='" + email + '\'' +
                ", turma=" + turma +
                ", numeroAluno=" + numeroAluno +
                ", items=" + respostas +
                '}';
    }
}
