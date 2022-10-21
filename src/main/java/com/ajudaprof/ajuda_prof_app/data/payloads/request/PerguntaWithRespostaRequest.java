package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PerguntaWithRespostaRequest {

    @NotBlank
    @NotNull
    private Long idPergunta;

    @NotBlank
    @NotNull
    private List<RespostaRequest> respostas;

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public List<RespostaRequest> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaRequest> respostas) {
        this.respostas = respostas;
    }
}
