package com.ajudaprof.ajuda_prof_app.exception;

public enum ExceptionValues {
    TURMA_EXISTENTE("Escola, Ano e Sigla");

    String erros;
    ExceptionValues(String erros){
        this.erros = erros;
    }

    public String getValoresErro(){
        return erros;
    }
}
