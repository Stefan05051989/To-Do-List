package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import java.io.IOException;

/**
 * JwtAuthenticationFilter
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security
 *
 * @author Stefan Kiers
 * @since 4-9-2026
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(
            JwtUtil jwtUtil,
            UserRepository userRepository
    ) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = parseToken(httpServletRequest);
        System.out.println("DEBUG - token aanwezig: " + (token != null));
        if (token != null) {
            boolean tokenValid = jwtUtil.isTokenValid(token);
            System.out.println("DEBUG - isTokenValid: " + tokenValid);
            if (tokenValid) {
                String email = jwtUtil.extractEmail(token);
                System.out.println("DEBUG - email uit JWT: " + email);
                Optional<User> userOptional = userRepository.findByEmail(email);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    System.out.println("DEBUG - user gevonden: " + user.getEmail());
                    List<SimpleGrantedAuthority> authorities;
                    if (user.isAdmin()) {
                        authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        System.out.println("DEBUG - gebruiker is ADMIN");
                    } else {
                        authorities = List.of();
                        System.out.println("DEBUG - gebruiker is GEEN ADMIN");
                    }

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("DEBUG - authentication aangemaakt: " + authentication.getName());
                    System.out.println("DEBUG - authorities: " + authentication.getAuthorities());
                } else {
                    System.out.println("DEBUG - GEEN USER GEVONDEN VOOR: " + email);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseToken(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}