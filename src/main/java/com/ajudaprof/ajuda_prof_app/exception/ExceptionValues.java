package com.ajudaprof.ajuda_prof_app.exception;

public enum ExceptionValues {
    TURMA_EXISTENTE("Escola, Ano e Sigla"),

    TURMA_INEXISTENTE("Escola, Ano e Sigla"),
    ALUNO_EXISTENTE("Primeiro Nome, Último Nome, Turma e Número"),
    ALUNO_EXISTENTE_OU_NAO("Primeiro Nome, Último Nome, Turma e Número"),
    ALUNO_INEXISTENTE("Primeiro Nome e Último Nome"),
    INEXISTENTE("Sem informação");

    String erros;
    ExceptionValues(String erros){
        this.erros = erros;
    }

    public String getValoresErro(){
        return erros;
    }
}
