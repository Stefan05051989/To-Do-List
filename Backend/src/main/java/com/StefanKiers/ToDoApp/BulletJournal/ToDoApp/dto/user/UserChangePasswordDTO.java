package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.validation.ValidPassword;

public record UserChangePasswordDTO(
        String email,
        String currentPassword,
        @ValidPassword
        String newPassword
) {
}