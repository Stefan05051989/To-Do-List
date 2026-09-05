package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.config;


import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfiguration
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.config
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // enables pre authorize.
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(((request, response, authException) ->
                                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"))))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
