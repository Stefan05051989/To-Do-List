package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login.LoginRequestDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
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
        /* TODO:*/
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    /*
    Spring gebruikt de constructor om de AuthService te injecteren (Dependency Injection).
     Zonder constructor weet Spring niet hoe hij authService moet vullen,
     en krijg je een NullPointerException als je /auth/login aanroept.
     */

    public boolean login(LoginRequestDTO request){
        return userRepository.findByEmail(request.email())
                .map(user -> passwordEncoder.matches(request.password(),
                        user.getPassword()))
                .orElse(false);
    }

}
