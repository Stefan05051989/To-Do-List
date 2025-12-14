package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

// geen wachtwoord ivm beveiliging
public record UserSummaryDTO(
    Long id,
    String firstName,
    String lastName,
    String email
) {}

