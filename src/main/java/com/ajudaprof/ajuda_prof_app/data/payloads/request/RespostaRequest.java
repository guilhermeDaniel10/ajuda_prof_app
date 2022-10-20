package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RespostaRequest {

    @NotBlank
    @NotNull
    private Long idAluno;

    @NotBlank
    @NotNull
    private Long idPergunta;

    @NotBlank
    @NotNull
    private Double cotacaoResposta;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public Double getCotacaoResposta() {
        return cotacaoResposta;
    }

    public void setCotacaoResposta(Double cotacaoResposta) {
        this.cotacaoResposta = cotacaoResposta;
    }
}
