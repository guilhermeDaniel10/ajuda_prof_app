package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTeste;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @OneToMany(mappedBy = "teste")
    private Set<Pergunta> perguntas;
}
