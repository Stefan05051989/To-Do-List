package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountCreateDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.account
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
public record AccountCreateDTO(
        String username,
        String password,
        @JsonProperty("display_name")
        String displayName
) {
}
