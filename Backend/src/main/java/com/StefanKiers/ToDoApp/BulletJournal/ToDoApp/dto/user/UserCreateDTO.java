/*
CreateDTO geeft waarde
 */

package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.validation.ValidPassword;

public record UserCreateDTO(
        String firstName,
        String lastName,
        String email,
        @ValidPassword
        String password
) {}
