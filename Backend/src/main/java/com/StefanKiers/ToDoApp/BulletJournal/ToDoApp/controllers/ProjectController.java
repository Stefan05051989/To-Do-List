package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProjectController
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 */
@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('SCRUM_MASTER', 'PRODUCT_OWNER') or @userSecurity.isAdmin(authentication)")
    public ResponseEntity<ProjectSummaryDTO> createProject(@RequestBody ProjectCreateDTO projectCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectCreateDTO));
    }
    @GetMapping
    public  ResponseEntity<List<ProjectSummaryDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectSummaryDTO> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SCRUM_MASTER', 'PRODUCT_OWNER') or @userSecurity.isAdmin(authentication)")
    public ResponseEntity<ProjectSummaryDTO> updateProject(@PathVariable Long id, @RequestBody ProjectUpdateDTO projectUpdateDTO) {
        return ResponseEntity.ok(projectService.updateProject(id, projectUpdateDTO));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("@userSecurity.isAdmin(authentication)")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
