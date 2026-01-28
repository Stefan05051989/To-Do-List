package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskUpdateDTO(
        @NotBlank
        @Size(max = 300)
        String title,
        @Size(max = 9000)
        String content,
        Status status
) {
}
