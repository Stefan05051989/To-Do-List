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

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = parseToken(httpServletRequest);
        System.out.println("DEBUG - token gevonden: " + token);
        if (token != null) {
            System.out.println("DEBUG - isTokenValid: " + jwtUtil.isTokenValid(token));
        }
        if (token != null && jwtUtil.isTokenValid(token)) {
            String email = jwtUtil.extractEmail(token);
            System.out.println("DEBUG - email: " + email);
            Optional<User> user = userRepository.findByEmail(email);
            System.out.println("DEBUG - user: " + user.get());
            if (user.isPresent()) {
                List<SimpleGrantedAuthority> authorities = user.get().isAdmin() ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) : List.of();

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.get(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("DEBUG - username: " + usernamePasswordAuthenticationToken);
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
