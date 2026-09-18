package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint;

import java.time.LocalDate;

/**
 * SprintCreateDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.Sprint
 *
 * @author Stefan Kiers
 * @since 9-9-2026
 */

public record SprintCreateDTO(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
