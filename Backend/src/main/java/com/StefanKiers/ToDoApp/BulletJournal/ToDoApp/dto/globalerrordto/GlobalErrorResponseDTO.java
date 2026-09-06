package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.globalerrordto;

import java.util.List;

public record GlobalErrorResponseDTO(
        String message,
        List<String> details
) {
}
