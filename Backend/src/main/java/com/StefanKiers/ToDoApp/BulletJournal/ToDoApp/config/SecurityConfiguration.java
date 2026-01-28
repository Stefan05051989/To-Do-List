package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
            /* TODO:
                - basic opzetten
                - bean uitzoeken
                - hierarchie opzetten
                - security filter chain (presentatie)
                -
                */

    // DEZE MOET NOG UITGEBREID WORDEN!
            @Bean
            public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
            }

//            @Bean
//            private Role role(){
//                return RoleHierarchyImpl.withDefaultRolePrefix()
//                        .role("ADMIN").implies("MANAGER");
//            }
            @Bean
            public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/**").permitAll()
                                        .anyRequest().authenticated()
                        );
//                http.csrf(csrf -> csrf.disable())
//                        .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/**").permitAll().anyRequest().authenticated());
                return http.build();
            }
}
