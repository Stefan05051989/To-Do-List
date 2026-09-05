package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role;

/**
 * ProjectMemberSummaryDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 */

public record ProjectMemberSummaryDTO(
        Long id,
        Long userId,
        String firstName,
        String lastName,
        Role role
) {
}
