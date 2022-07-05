package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Professor;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.TurmaRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.ProfessorService;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajudaprof.ajuda_prof_app.utils.StringParser;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaServiceImpl implements TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    ProfessorService professorService;

    @Override
    public MessageResponse createTurma(TurmaRequest turmaRequest) {
        Professor professor;
        try {
            professor = professorService.getProfessorByUsername(turmaRequest.getUsernameProfessor());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaRequest.getUsernameProfessor()};
            throw new ResourceNotFoundException("Turma", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        Turma repTurma;
        try {
            repTurma = getTurmaByInfo(turmaRequest.getUsernameProfessor(), turmaRequest.getAno(), turmaRequest.getSigla());
            String[] arrObj = {turmaRequest.getUsernameProfessor(), turmaRequest.getAno().toString(), turmaRequest.getSigla()};
            throw new RepeatedResourceException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } catch (ResourceNotFoundException ex) {
            Turma newTurma = new Turma();
            newTurma.setProfessor(professor);
            newTurma.setAno(turmaRequest.getAno());
            newTurma.setSigla(turmaRequest.getSigla());
            turmaRepository.save(newTurma);
            return new MessageResponse("Nova turma adicionada");
        }
    }


    @Override
    public Optional<Turma> updateTurma(Integer idTurma, TurmaRequest turmaRequest) {
        Optional<Turma> turma = turmaRepository.findById(idTurma);
        if (turma.isEmpty()) {
            throw new ResourceNotFoundException("Turma", "id", idTurma);
        }
        Professor professor;
        try {
            professor = professorService.getProfessorByUsername(turmaRequest.getUsernameProfessor());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaRequest.getUsernameProfessor()};
            throw new ResourceNotFoundException("Turma", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        Turma repTurma;
        try {
            repTurma = getTurmaByInfo(turmaRequest.getUsernameProfessor(), turmaRequest.getAno(), turmaRequest.getSigla());
            String[] arrObj = {turmaRequest.getUsernameProfessor(), turmaRequest.getAno().toString(), turmaRequest.getSigla()};
            throw new RepeatedResourceException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } catch (ResourceNotFoundException ex) {
            turma.get().setProfessor(professor);
            turma.get().setAno(turmaRequest.getAno());
            turma.get().setSigla(turmaRequest.getSigla());
            turmaRepository.save(turma.get());
            return turma;
        }
    }

    @Override
    public Turma updateTurmaByInfo(String usernameProfessor, Short ano, String sigla, TurmaRequest turmaRequest) {
        Turma turma;
        try {
            turma = getTurmaByInfo(usernameProfessor, ano, sigla);
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {usernameProfessor, ano.toString(), sigla};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        Professor professor;
        try {
            professor = professorService.getProfessorByUsername(turmaRequest.getUsernameProfessor());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaRequest.getUsernameProfessor()};
            throw new ResourceNotFoundException("Turma", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        Turma repTurma;
        try {
            repTurma = getTurmaByInfo(turmaRequest.getUsernameProfessor(), turmaRequest.getAno(), turmaRequest.getSigla());
            String[] arrObj = {turmaRequest.getUsernameProfessor(), turmaRequest.getAno().toString(), turmaRequest.getSigla()};
            throw new RepeatedResourceException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } catch (ResourceNotFoundException ex) {
            turma.setProfessor(professor);
            turma.setAno(turmaRequest.getAno());
            turma.setSigla(turmaRequest.getSigla());
            turmaRepository.save(turma);
            return turma;
        }
    }

    @Override
    public void deleteTurma(Integer idTurma) throws ResourceNotFoundException {
        if (turmaRepository.findById(idTurma) != null) {
            turmaRepository.deleteById(idTurma);
        } else throw new ResourceNotFoundException("Turma", "id", idTurma);
    }

    @Override
    public void deleteTurmaByInfo(String usernameProfessor, Short ano, String sigla) {
        Turma turma = this.getTurmaByInfo(usernameProfessor, ano, sigla);
        if (turma == null) {
            String[] arrObj = {usernameProfessor, ano.toString(), sigla};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        turmaRepository.delete(turma);
    }

    @Override
    public Turma getASingleTurma(Integer idTurma) {
        return turmaRepository.findById(idTurma).orElseThrow(() -> new ResourceNotFoundException("Turma", "id", idTurma));
    }

    @Override
    public List<Turma> getAllTurma() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma getTurmaByInfo(String usernameProfessor, Short ano, String sigla) {
        Professor professor;
        try {
            professor = professorService.getProfessorByUsername(usernameProfessor);
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {usernameProfessor};
            throw new ResourceNotFoundException("Turma", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        Turma turma = turmaRepository.findByProfessorAndAnoAndSigla(professor, ano, sigla);
        if (turma == null) {
            String[] arrObj = {professor.getUsername(), ano.toString(), sigla};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return turma;
    }

    @Override
    public List<Turma> getTurmaByProfessor(String usernameProfessor) {
        Professor professor;
        try {
            professor = professorService.getProfessorByUsername(usernameProfessor);
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {usernameProfessor};
            throw new ResourceNotFoundException("Turma", ExceptionValues.PROFESSOR_INEXISTENTE_USERNAME.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return turmaRepository.findByProfessor(professor);
    }
}
