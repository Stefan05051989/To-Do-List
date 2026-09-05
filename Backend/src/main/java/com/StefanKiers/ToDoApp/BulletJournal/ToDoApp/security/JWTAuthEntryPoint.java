package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWTAuthEntryPoint
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 *
 * klasse doet één ding : reageren op niet-geautoriseerde aanvraag met 401.
 */

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "unauthorized.");
    }
}
