package com.ajudaprof.ajuda_prof_app.data.repository;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Pergunta;
import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Resposta findByAlunoAndPergunta(Aluno aluno, Pergunta pergunta);
}
