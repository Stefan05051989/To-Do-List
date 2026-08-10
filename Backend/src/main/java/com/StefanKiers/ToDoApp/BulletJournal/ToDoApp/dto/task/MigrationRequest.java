package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.MigrationTarget;

/**
 * MigrationRequest
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task
 *
 * @author Stefan Kiers
 * @since 5-8-2026
 * /* TODO:
 */


public record MigrationRequest(
        MigrationTarget target
) {
}
