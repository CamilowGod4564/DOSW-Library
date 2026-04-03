package edu.eci.dosw.tdd.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "DOSW library", version = "1.0"),
        security = @SecurityRequirement(name = "basicAuth")  // Cambiado a basicAuth
)
@SecurityScheme(
        name = "basicAuth",  // Cambiado el nombre
        type = SecuritySchemeType.HTTP,
        scheme = "basic"     // ← BASIC, no bearer
)
public class OpenApiConfig {
}