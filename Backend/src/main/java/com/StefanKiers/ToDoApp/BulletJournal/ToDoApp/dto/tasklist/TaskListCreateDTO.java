package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.TaskList;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;

public record TaskListCreateDTO(
        String title,
        Long userId
) {}
