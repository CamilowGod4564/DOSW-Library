package edu.eci.dosw.tdd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desactivar CSRF (necesario para API stateless)
                .csrf(csrf -> csrf.disable())

                // 2. Sin sesiones (stateless)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Desactivar formulario de login
                .formLogin(form -> form.disable())

                // 4. Desactivar Basic Auth (importante para que no interfiera)
                .httpBasic(basic -> basic.disable())

                // 5. Agregar tu filtro JWT
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // 6. Configurar qué endpoints son públicos
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",           // Login, registro
                                "/swagger-ui/**",         // Swagger UI
                                "/swagger-ui.html",       // Swagger UI
                                "/v3/api-docs/**",        // Documentación OpenAPI
                                "/v3/api-docs"            // Documentación OpenAPI
                        ).permitAll()                  // ← Estos NO necesitan JWT
                        .anyRequest().authenticated()  // ← Todo lo demás SÍ necesita JWT
                )

                // 7. Manejo de errores (opcional pero recomendado)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(401);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Token no válido o no proporcionado\"}");
                        })
                );

        return http.build();
    }
}