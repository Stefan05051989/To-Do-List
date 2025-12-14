/*
CreateDTO geeft waarde
 */

package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

public record UserCreateDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {}
