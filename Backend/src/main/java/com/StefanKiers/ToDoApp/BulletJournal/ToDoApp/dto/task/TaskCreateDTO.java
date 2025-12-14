package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Status;

public record TaskCreateDTO(
        String title,
        String content,
        Long taskListId,
        Status status
) {}
