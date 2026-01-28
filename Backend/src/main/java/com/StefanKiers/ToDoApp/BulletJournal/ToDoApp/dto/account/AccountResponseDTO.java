package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * AccountResponseDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.account
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
public record AccountResponseDTO(
        Long id,
        String username,
        @JsonProperty("display_name")
        String displayName,
        @JsonProperty("creation_date")
        OffsetDateTime creationDate
) {
}
