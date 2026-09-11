package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.Sprint;

import java.time.LocalDate;

/**
 * SprintUpdateDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.Sprint
 *
 * @author Stefan Kiers
 * @since 9-9-2026
 */


public record SprintUpdateDTO(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
