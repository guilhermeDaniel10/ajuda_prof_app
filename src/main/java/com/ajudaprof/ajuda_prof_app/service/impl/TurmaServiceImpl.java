package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.TurmaRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.TurmaRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceAlreadyExists;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajudaprof.ajuda_prof_app.utils.StringParser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaServiceImpl implements TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    @Override
    public MessageResponse createTurma(TurmaRequest turmaRequest) {
        try {
            Turma turma = getTurmaByInfo(turmaRequest.getEscola(), turmaRequest.getAno(), turmaRequest.getSigla());
            String[] arrObj = {turmaRequest.getEscola(), turmaRequest.getAno().toString(), turmaRequest.getSigla()};

            throw new ResourceAlreadyExists("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } catch (ResourceNotFoundException ex) {
            Turma newTurma = new Turma();
            newTurma.setEscola(turmaRequest.getEscola());
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
        turma.get().setEscola(turmaRequest.getEscola());
        turma.get().setAno(turmaRequest.getAno());
        turma.get().setSigla(turmaRequest.getSigla());
        turmaRepository.save(turma.get());
        return turma;
    }

    @Override
    public Turma updateTurmaByInfo(String escola, Short ano, String sigla, TurmaRequest turmaRequest) {
        Turma turma;
        try {
            turma = getTurmaByInfo(escola, ano, sigla);
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {escola, ano.toString(), sigla};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        try {
            Turma requestTurma = this.getTurmaByInfo(turmaRequest.getEscola(), turmaRequest.getAno(), turmaRequest.getSigla());
            String[] arrObj = {requestTurma.getEscola(), requestTurma.getAno().toString(), requestTurma.getSigla()};
            throw new RepeatedResourceException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } catch (ResourceNotFoundException ex) {

            turma.setEscola(turmaRequest.getEscola());
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
    public void deleteTurmaByInfo(String escola, Short ano, String sigla) {
        Turma turma = this.getTurmaByInfo(escola, ano, sigla);
        if (turma == null) {
            String[] arrObj = {escola, ano.toString(), sigla};
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
    public Turma getTurmaByInfo(String escola, Short ano, String sigla) {
        Turma turma = turmaRepository.findByEscolaAndAnoAndSigla(escola, ano, sigla);
        if (turma == null) {
            String[] arrObj = {escola, ano.toString(), sigla};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return turma;
    }
}
