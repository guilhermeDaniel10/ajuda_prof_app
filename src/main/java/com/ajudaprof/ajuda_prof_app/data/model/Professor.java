package com.ajudaprof.ajuda_prof_app.data.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProfessor;

    @Column(unique = true)
    @Size(min = 5)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    @Size(min = 5)
    private String password;

    @Column
    private String escola;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private Set<Turma> turmas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "idProfessor"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Professor(){

    }

    public Professor(String username, String email, String password, String escola) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.escola = escola;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        Professor professor = (Professor) o;
        return username.equals(professor.username) && email.equals(professor.email) && password.equals(professor.password) && escola.equals(professor.escola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, escola);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", escola='" + escola + '\'' +
                '}';
    }
}
