package com.ajudaprof.ajuda_prof_app.data.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ProfessorDTO {

    private String username;

    private String email;

    private String escola;

    public ProfessorDTO(String username, String email, String escola) {
        this.username = username;
        this.email = email;
        this.escola = escola;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorDTO that = (ProfessorDTO) o;
        return username.equals(that.username) && email.equals(that.email) && escola.equals(that.escola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, escola);
    }

    @Override
    public String toString() {
        return "ProfessorDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", escola='" + escola + '\'' +
                '}';
    }
}
