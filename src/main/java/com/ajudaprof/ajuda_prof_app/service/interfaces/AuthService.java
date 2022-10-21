package com.ajudaprof.ajuda_prof_app.service.interfaces;

import com.ajudaprof.ajuda_prof_app.data.payloads.request.LoginRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.request.ProfessorRequest;
import com.ajudaprof.ajuda_prof_app.data.payloads.response.JWTAuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    public ResponseEntity<?> registerProfessor(@RequestBody ProfessorRequest professorDto);

    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginRequest loginDto);

}
