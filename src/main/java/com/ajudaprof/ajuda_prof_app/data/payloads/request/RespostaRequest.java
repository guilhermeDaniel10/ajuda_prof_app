package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RespostaRequest {

    @NotBlank
    @NotNull
    private Long idAluno;

    @NotBlank
    @NotNull
    private Double cotacaoResposta;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Double getCotacaoResposta() {
        return cotacaoResposta;
    }

    public void setCotacaoResposta(Double cotacaoResposta) {
        this.cotacaoResposta = cotacaoResposta;
    }
}
