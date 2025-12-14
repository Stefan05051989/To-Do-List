package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

public record UserChangePasswordDTO(
        String email,
        String currentPassword,
        String newPassword
) {
}