package com.ajudaprof.ajuda_prof_app.data.payloads.request;

import javax.persistence.Column;

public class AlunoRequest {
    private String primeiroNome;
    @Column
    private String ultimoNome;
    @Column
    private String email;
}
