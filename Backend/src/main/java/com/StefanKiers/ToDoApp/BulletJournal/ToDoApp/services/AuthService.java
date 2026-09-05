package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login.LoginRequestDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login.LoginResponseDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.UserMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthController
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new ResourceNotFoundException("Ongeldige inloggegevens"));

        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new ResourceNotFoundException("Ongeldige inloggegevens");
        }
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        UserSummaryDTO userSummaryDTO = userMapper.toUserSummaryDTO(user);
        return new LoginResponseDTO(accessToken, refreshToken, userSummaryDTO);
    }

    public LoginResponseDTO refresh(String refreshToken) {
        if (!jwtUtil.isTokenValid(refreshToken) || !jwtUtil.isRefreshToken(refreshToken)) {
            throw new ResourceNotFoundException("Ongeldige of verlopen");
        }
        String email = jwtUtil.extractEmail(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Gebruiker niet gevonden"));

        String newAccessToken = jwtUtil.generateAccessToken(user);
        UserSummaryDTO userSummaryDTO = userMapper.toUserSummaryDTO(user);
        return new LoginResponseDTO(newAccessToken, refreshToken, userSummaryDTO);
    }
}

