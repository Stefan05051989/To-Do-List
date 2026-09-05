package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JwtUtil
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.Security
 *
 * @author Stefan Kiers
 * @since 4-9-2026
 */

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-expiration}")
    private long accessExpirationMs;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpirationMs;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {
        return Jwts.builder().subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("isAdmin", user.isAdmin())
                .claim("type", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ accessExpirationMs))
                .signWith(signingKey)
                .compact();
    }
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .signWith(signingKey)
                .compact();
    }
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        }catch (JwtException | IllegalArgumentException exception) {
//            System.out.println("DEBUG: JWT Token is invalid" + exception.getClass());
            return false;
        }
    }
    public boolean isRefreshToken(String token) {
        return "refresh".equals(extractClaims(token).get("type", String.class));
    }
}
