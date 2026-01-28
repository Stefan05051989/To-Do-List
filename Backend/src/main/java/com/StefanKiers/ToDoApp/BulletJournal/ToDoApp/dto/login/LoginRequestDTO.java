package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login;

/**
 * LoginRequestDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
public record LoginRequestDTO(
        String email,
        String password
) {
}
