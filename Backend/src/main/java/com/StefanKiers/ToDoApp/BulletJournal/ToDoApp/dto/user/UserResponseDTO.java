package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {}
