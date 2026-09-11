package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.SprintStatus;

import java.time.LocalDate;

/**
 * SprintSummaryDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.Sprint
 *
 * @author Stefan Kiers
 * @since 9-9-2026
 */


public record SprintSummaryDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        SprintStatus sprintStatus,
        Long projectId
) {
}
