package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project;
import java.time.LocalDate;

/**
 * ProjectUpdateDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 */

public record ProjectUpdateDTO(
        String title,
        LocalDate deadline
) {
}
