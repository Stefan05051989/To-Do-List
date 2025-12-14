package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist;

public record TaskListDTO(
        Long id,
        String title,
        Long userId
) {
}


/*
DTO Summary + ""DTO geeft data terug, anderen niet.
als we teruggeven fromEntity, krijgen toUserEntity method
in service werken we met -> entiteiten
in controller werken we met -> dto's S

 */