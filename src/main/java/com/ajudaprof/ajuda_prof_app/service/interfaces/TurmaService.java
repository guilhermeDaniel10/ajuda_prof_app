package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TurmaService {
    MessageResponse createTurma(TurmaRequest turmaRequest);
    Optional<Turma> updateTurma(Integer idTurma, TurmaRequest turmaRequest);
    Turma updateTurmaByInfo(String usernameProfessor, Short ano, String sigla, TurmaRequest turmaRequest);
    void deleteTurma(Integer idTurma);
    void deleteTurmaByInfo(String usernameProfessor, Short ano, String sigla);
    Turma getASingleTurma(Integer idTurma);
    List<Turma> getAllTurma();
    Turma getTurmaByInfo(String usernameProfessor, Short ano, String sigla);
    List<Turma> getTurmaByProfessor(String usernameProfessor);
}
