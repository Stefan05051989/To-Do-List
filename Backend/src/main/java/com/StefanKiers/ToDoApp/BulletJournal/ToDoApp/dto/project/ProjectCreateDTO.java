package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project;

import java.time.LocalDate;

/**
 * ProjectCreateDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 *
 */

public record ProjectCreateDTO(
        String title,
        LocalDate deadline
) {
}
