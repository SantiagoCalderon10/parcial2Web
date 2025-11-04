package com.example.GestionAsignaturas.configuracion;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI tareaApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion Asignaturas MVC")
                        .description("Documentación CRUD de asignaturas")
                        .version("1.0.0"));
    }
}

