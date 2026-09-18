package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.SprintService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SprintController
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers
 *
 * @author Stefan Kiers
 * @since 16-9-2026
 */

@RestController
@CrossOrigin(origins = "*")
public class SprintController {
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping("/project/{projectId}/sprint")
    @PreAuthorize("@userSecurity.canManageProject(authentication, #projectId)")
    public ResponseEntity<SprintSummaryDTO> createSprint(@PathVariable Long projectId, @RequestBody SprintCreateDTO sprintCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sprintService.createSprint(projectId, sprintCreateDTO));
    }

    @GetMapping("/project/{projectId}/sprint")
    public  ResponseEntity<List<SprintSummaryDTO>> getSprintsByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(sprintService.getSprintsProjectById(projectId));
    }
    @GetMapping("/sprint/{id}")
    public  ResponseEntity<SprintSummaryDTO> getSprintById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getSprintById(id));
    }
    @PutMapping("/sprint/{id}")
    @PreAuthorize("@userSecurity.canManageSprintById(authentication, #id)")
    public ResponseEntity<SprintSummaryDTO> updateSprint(@PathVariable Long id, @RequestBody SprintUpdateDTO sprintUpdateDTO) {
        return ResponseEntity.ok(sprintService.updateSprint(id, sprintUpdateDTO));
    }
    @PostMapping("/sprint/{id}/cancel")
    @PreAuthorize("@userSecurity.hasSprintRole(authentication, #id, T(com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role).PRODUCT_OWNER)")
    public ResponseEntity<SprintSummaryDTO> cancelSprint(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.cancelSprint(id));
    }
    @DeleteMapping("/sprint/{id}")
    @PreAuthorize("@userSecurity.isAdmin(authentication)")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}