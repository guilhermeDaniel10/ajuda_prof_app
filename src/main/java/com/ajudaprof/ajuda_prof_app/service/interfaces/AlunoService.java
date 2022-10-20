package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.model.dto.TurmaDTO;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.AlunoRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AlunoService {
    MessageResponse createAluno(AlunoRequest alunoRequest);

    MessageResponse createMultipleAlunos(List<AlunoRequest> alunoRequest);
    Aluno updateAluno(TurmaDTO turma, Integer numeroAluno, AlunoRequest alunoRequest);

    Aluno updateAlunoById(Long idAluno, AlunoRequest alunoRequest);
    List<Aluno> getAlunoByNome(String primeiroNome, String ultimoNome);
    Aluno getAlunoByTurmaNumero(TurmaDTO turma, Integer numeroAluno);
    List<Aluno> getAllAlunosByTurma(TurmaDTO turmaDTO);
    Aluno getAlunoById(Long idAluno);
    Aluno getAlunoByEmail(String email);
    void deleteAluno(Long idAluno);
    void deleteAlunoByNumeroTurma(TurmaDTO turmaDTO, Integer numeroAluno);
    List<Aluno> getAllAlunos();

    void boostrapAlunos() throws IOException;
}
