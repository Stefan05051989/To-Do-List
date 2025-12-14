package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskSummaryDTO;

import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        List<TaskSummaryDTO> tasks
) { }
