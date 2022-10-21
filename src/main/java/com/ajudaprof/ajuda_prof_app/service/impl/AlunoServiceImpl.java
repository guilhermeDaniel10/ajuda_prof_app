package com.ajudaprof.ajuda_prof_app.service.impl;

import com.ajudaprof.ajuda_prof_app.data.model.Aluno;
import com.ajudaprof.ajuda_prof_app.data.model.Turma;
import com.ajudaprof.ajuda_prof_app.data.model.dto.AlunoDTO;
import com.ajudaprof.ajuda_prof_app.data.model.dto.TurmaDTO;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.AlunoRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.MessageResponse;
import com.ajudaprof.ajuda_prof_app.data.repository.AlunoRepository;
import com.ajudaprof.ajuda_prof_app.exception.ExceptionValues;
import com.ajudaprof.ajuda_prof_app.exception.RepeatedResourceException;
import com.ajudaprof.ajuda_prof_app.exception.ResourceNotFoundException;
import com.ajudaprof.ajuda_prof_app.service.interfaces.AlunoService;
import com.ajudaprof.ajuda_prof_app.service.interfaces.TurmaService;
import com.ajudaprof.ajuda_prof_app.utils.StringParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TurmaService turmaService;

    @Override
    public MessageResponse createAluno(AlunoRequest alunoRequest) {

        TurmaDTO turmaDTO = new TurmaDTO(alunoRequest.getUsernameProfessor(), alunoRequest.getAno(), alunoRequest.getSigla());
        Turma turma;
        try {
            turma = turmaService.getTurmaByInfo(turmaDTO.getUsernameProfessor(), turmaDTO.getAno(), turmaDTO.getSigla());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaDTO.getUsernameProfessor(), turmaDTO.getAno().toString(), turmaDTO.getSigla()};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        Aluno aluno;
        try {
            aluno = this.getAlunoByTurmaNumero(turmaDTO, alunoRequest.getNumeroAluno());
            String[] arrObj = {alunoRequest.getPrimeiroNome(), alunoRequest.getUltimoNome(), turmaDTO.toStringDTO(), alunoRequest.getNumeroAluno().toString()};
            throw new RepeatedResourceException("Aluno", ExceptionValues.ALUNO_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        } catch (ResourceNotFoundException ex) {
            Aluno newAluno = new Aluno();
            newAluno.setPrimeiroNome(alunoRequest.getPrimeiroNome());
            newAluno.setUltimoNome(alunoRequest.getUltimoNome());
            newAluno.setEmail(alunoRequest.getEmail());
            newAluno.setTurma(turma);
            newAluno.setNumeroAluno(alunoRequest.getNumeroAluno());

            alunoRepository.save(newAluno);
            return new MessageResponse("Novo aluno adicionado");
        }
    }

    @Override
    public MessageResponse createMultipleAlunos(List<AlunoRequest> alunoRequest) {
        for(AlunoRequest a : alunoRequest){
            this.createAluno(a);
        }
        return new MessageResponse("Novos alunos adicionados");
    }

    @Override
    public Aluno updateAluno(TurmaDTO turmaDTO, Integer numeroAluno, AlunoRequest alunoRequest) {
        Aluno aluno;
        Turma turma;
        try {
            aluno = this.getAlunoByTurmaNumero(turmaDTO, numeroAluno);

        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {alunoRequest.getPrimeiroNome(), alunoRequest.getUltimoNome(), turmaDTO.toStringDTO(), alunoRequest.getNumeroAluno().toString()};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_EXISTENTE_OU_NAO.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        TurmaDTO updateTurmaDTO = new TurmaDTO(alunoRequest.getUsernameProfessor(), alunoRequest.getAno(), alunoRequest.getSigla());
        Turma updateTurma;
        try {
            updateTurma = this.turmaService.getTurmaByInfo(updateTurmaDTO.getUsernameProfessor(), updateTurmaDTO.getAno(), updateTurmaDTO.getSigla());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {updateTurmaDTO.toStringDTO()};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_INEXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        aluno = this.getAlunoByTurmaNumero(turmaDTO, numeroAluno);
        aluno.setNumeroAluno(alunoRequest.getNumeroAluno());
        aluno.setTurma(updateTurma);
        aluno.setEmail(alunoRequest.getEmail());
        aluno.setPrimeiroNome(alunoRequest.getPrimeiroNome());
        aluno.setUltimoNome(alunoRequest.getUltimoNome());
        alunoRepository.save(aluno);
        return aluno;
    }

    @Override
    public Aluno updateAlunoById(Long idAluno, AlunoRequest alunoRequest) {
        Aluno aluno;
        Turma turma;
        try {
            aluno = this.getAlunoById(idAluno);

        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {String.valueOf(idAluno)};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_INEXISTENTE_ID.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        TurmaDTO updateTurmaDTO = new TurmaDTO(alunoRequest.getUsernameProfessor(), alunoRequest.getAno(), alunoRequest.getSigla());
        Turma updateTurma;
        try {
            updateTurma = this.turmaService.getTurmaByInfo(updateTurmaDTO.getUsernameProfessor(), updateTurmaDTO.getAno(), updateTurmaDTO.getSigla());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {updateTurmaDTO.toStringDTO()};
            throw new ResourceNotFoundException("Turma", ExceptionValues.TURMA_INEXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }

        aluno.setNumeroAluno(alunoRequest.getNumeroAluno());
        aluno.setTurma(updateTurma);
        aluno.setEmail(alunoRequest.getEmail());
        aluno.setPrimeiroNome(alunoRequest.getPrimeiroNome());
        aluno.setUltimoNome(alunoRequest.getUltimoNome());
        alunoRepository.save(aluno);
        return aluno;
    }

    @Override
    public List<Aluno> getAlunoByNome(String primeiroNome, String ultimoNome) {
        List<Aluno> alunos = alunoRepository.findByPrimeiroNomeAndUltimoNome(primeiroNome, ultimoNome);

        if (alunos == null || alunos.isEmpty()) {
            String[] arrObj = {primeiroNome, ultimoNome};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_INEXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return alunos;
    }

    @Override
    public Aluno getAlunoByTurmaNumero(TurmaDTO turmaDTO, Integer numeroAluno) {
        Turma turma;
        try {
            turma = turmaService.getTurmaByInfo(turmaDTO.getUsernameProfessor(), turmaDTO.getAno(), turmaDTO.getSigla());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaDTO.getUsernameProfessor(), turmaDTO.getAno().toString(), turmaDTO.getSigla()};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        Aluno aluno = alunoRepository.findByTurmaAndNumeroAluno(turma, numeroAluno);

        if (aluno == null) {
            String[] arrObj = {turmaDTO.toStringDTO(), numeroAluno.toString()};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return aluno;
    }

    @Override
    public List<Aluno> getAllAlunosByTurma(TurmaDTO turmaDTO) {
        Turma turma;
        try {
            turma = turmaService.getTurmaByInfo(turmaDTO.getUsernameProfessor(), turmaDTO.getAno(), turmaDTO.getSigla());
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaDTO.getUsernameProfessor(), turmaDTO.getAno().toString(), turmaDTO.getSigla()};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.TURMA_EXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        List<Aluno> alunos = alunoRepository.findByTurma(turma);
        if (alunos == null || alunos.isEmpty()) {
            String[] arrObj = {turmaDTO.toStringDTO()};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.TURMA_SEM_ALUNOS.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return alunos;
    }

    @Override
    public Aluno getAlunoById(Long idAluno) {
        return alunoRepository.findById(idAluno).orElseThrow(() -> new ResourceNotFoundException("Aluno", "id", idAluno));
    }

    @Override
    public Aluno getAlunoByEmail(String email) {
        Aluno aluno = alunoRepository.findByEmail(email);
        if (aluno == null) {
            String[] arrObj = {email};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.ALUNO_INEXISTENTE_EMAIL.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
        return aluno;
    }


    @Override
    public void deleteAluno(Long idAluno) {
        if (alunoRepository.findById(idAluno) != null) {
            alunoRepository.deleteById(idAluno);
        } else throw new ResourceNotFoundException("Aluno", "id", idAluno);
    }

    @Override
    public void deleteAlunoByNumeroTurma(TurmaDTO turmaDTO, Integer numeroAluno) {
        try {
            Aluno aluno = this.getAlunoByTurmaNumero(turmaDTO, numeroAluno);
            alunoRepository.delete(aluno);
        } catch (ResourceNotFoundException ex) {
            String[] arrObj = {turmaDTO.toStringDTO(), numeroAluno.toString()};
            throw new ResourceNotFoundException("Aluno", ExceptionValues.TURMA_ALUNO_INEXISTENTE.getValoresErro(), StringParser.stringArrayToString(arrObj));
        }
    }

    @Override
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @Override
    public void boostrapAlunos() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<AlunoDTO> listAluno = objectMapper.readValue(new File("src/main/resources/alunos.json"), new TypeReference<List<AlunoDTO>>() {
        });
        Turma turma = turmaService.getTurmaByInfo(listAluno.get(0).getUsernameProfessor(), listAluno.get(0).getAno(), listAluno.get(0).getSigla());

        for (AlunoDTO alunoDto : listAluno) {

            Aluno newAluno = new Aluno();
            newAluno.setPrimeiroNome(alunoDto.getPrimeiroNome());
            newAluno.setUltimoNome(alunoDto.getUltimoNome());
            newAluno.setEmail(alunoDto.getEmail());
            newAluno.setTurma(turma);
            newAluno.setNumeroAluno(alunoDto.getNumeroAluno());

            alunoRepository.save(newAluno);
        }


    }

}
