package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.dto.TurmaDTO;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.AlunoRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;

import java.util.List;

public interface ProfessorService {

    MessageResponse createProfessor(ProfessorRequest professorRequest);
    Professor updateProfessorByEmail(String email, ProfessorRequest professorRequest);
    Professor updateProfessorByUsername(String username, ProfessorRequest professorRequest);
    Professor updateProfessorById(Long idProfessor, ProfessorRequest professorRequest);
    Professor getProfessorByUsername(String username);
    Professor getProfessorById(Long idProfessor);
    Professor getProfessorByEmail(String email);
    void deleteProfessorById(Long idProfessor);
    void deleteProfessorByUsername(String username);
    void deleteProfessorByEmail(String email);
    List<Professor> getAllProfessores();

}
