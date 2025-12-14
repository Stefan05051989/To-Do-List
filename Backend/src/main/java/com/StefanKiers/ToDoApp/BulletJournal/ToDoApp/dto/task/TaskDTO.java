package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task;

public record TaskDTO(
        Long id,
        String title,
        String content,
        Long taskListId
) {
}
