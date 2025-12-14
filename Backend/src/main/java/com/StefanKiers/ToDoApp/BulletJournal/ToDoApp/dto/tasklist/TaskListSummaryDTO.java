package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.TaskList;

public record TaskListSummaryDTO(
        Long id,
        String title
) {}
