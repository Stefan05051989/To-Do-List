package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserSummaryDTO;

/**
 * LoginResponseDTO
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.login
 *
 * @author Stefan Kiers
 * @since 4-9-2026
 * /* TODO:
 */

public record LoginResponseDTO(
        String accessToken,
        String refreshToken,
        UserSummaryDTO userSummaryDTO
) {
}
