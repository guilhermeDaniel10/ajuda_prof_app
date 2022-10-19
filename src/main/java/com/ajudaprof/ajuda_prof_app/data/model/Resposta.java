package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResposta;

    @ManyToOne
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;

    @Column
    private Double cotacaoResposta;




}
