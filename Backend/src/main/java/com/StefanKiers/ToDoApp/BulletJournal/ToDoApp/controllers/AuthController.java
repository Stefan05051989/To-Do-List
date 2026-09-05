package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login.LoginRequestDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login.LoginResponseDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login.RefreshRequestDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@RequestBody RefreshRequestDTO refreshRequestDTO) {
        return ResponseEntity.ok(authService.refresh(refreshRequestDTO.refreshToken()));
    }
}

