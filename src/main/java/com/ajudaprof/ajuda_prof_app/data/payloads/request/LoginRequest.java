package com.ajudaprof.ajuda_prof_app.data.payloads.request;
import lombok.Data;

@Data
public class LoginRequest {
    private String usernameOrEmail;
    private String password;
}
