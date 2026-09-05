package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember;

/**
 * ProjectMemberRoleUpdateDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 */

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role;

public record ProjectMemberRoleUpdateDTO(
        Role role
) {
}
