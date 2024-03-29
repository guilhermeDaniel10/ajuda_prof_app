package com.ajudaprof.ajuda_prof_app.exception;

public enum ExceptionValues {
    TURMA_EXISTENTE("Professor, Ano e Sigla"),
    TESTE_EXISTENTE("Teste"),

    PROFESSOR_EXISTENTE("Professor"),

    PROFESSOR_EXISTENTE_USERNAME("Username Professor"),

    PROFESSOR_EXISTENTE_EMAIL("Email Professor"),

    PROFESSOR_EXISTENTE_ALL_INFO("inserted data"),

    TURMA_INEXISTENTE("Escola, Ano e Sigla"),

    TURMA_ID_INEXISTENTE("Id"),

    ALUNO_EXISTENTE("Primeiro Nome, Último Nome, Professor, Turma e Número"),

    TURMA_SEM_ALUNOS("Turma sem alunos"),

    TURMA_ALUNO_INEXISTENTE("Turma e Número"),

    ALUNO_EXISTENTE_OU_NAO("Primeiro Nome, Último Nome, Professor, Turma e Número"),

    ALUNO_INEXISTENTE_ID("Id"),

    ALUNO_INEXISTENTE_EMAIL("Email"),

    ALUNO_INEXISTENTE("Primeiro Nome e Último Nome"),

    PROFESSOR_INEXISTENTE_EMAIL("Email"),

    PROFESSOR_INEXISTENTE_USERNAME("Username"),

    PERGUNTA_REPETIDA("Pergunta"),

    INEXISTENTE("Sem informação");

    String erros;

    ExceptionValues(String erros) {
        this.erros = erros;
    }

    public String getValoresErro() {
        return erros;
    }
}
