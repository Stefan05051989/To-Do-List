package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Status;

public record TaskUpdateDTO(
        String title,
        String content,
        Status status
) {
}
