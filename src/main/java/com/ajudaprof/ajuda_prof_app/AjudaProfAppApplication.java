package com.ajudaprof.ajuda_prof_app;

import com.ajudaprof.ajuda_prof_app.data.model.Role;
import com.ajudaprof.ajuda_prof_app.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.mappers.ModelMapper;

@SpringBootApplication
public class AjudaProfAppApplication /*implements CommandLineRunner*/ {

    /*@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(AjudaProfAppApplication.class, args);
    }


    /*@Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

    }*/

}
