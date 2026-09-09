package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember.ProjectMemberAddDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember.ProjectMemberRoleUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember.ProjectMemberSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.ProjectMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProjectMemberController
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers
 *
 * @author Stefan Kiers
 * @since 7-9-2026
 */
@RestController
@RequestMapping("/project/{projectId}/member")
@CrossOrigin(origins = "*")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @PostMapping
    @PreAuthorize("@userSecurity.hasProjectRole(authentication, #projectId, T(com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role).SCRUM_MASTER) " +
                  "or @userSecurity.hasProjectRole(authentication, #projectId, T(com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role).PRODUCT_OWNER)")
    public ResponseEntity<ProjectMemberSummaryDTO> addMember(@PathVariable Long projectId, @RequestBody ProjectMemberAddDTO projectMemberAddDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.addMember(projectId, projectMemberAddDTO.userId()));
    }

    @GetMapping
    public ResponseEntity<List<ProjectMemberSummaryDTO>> getMembersByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.getByProjectId(projectId));
    }

    @PutMapping("/{userId}/role")
    @PreAuthorize("@userSecurity.isAdmin(authentication)")
    public ResponseEntity<ProjectMemberSummaryDTO> updateRole(@PathVariable Long projectId, @PathVariable Long userId,
                                                              @RequestBody ProjectMemberRoleUpdateDTO projectMemberRoleUpdateDTO) {
        return ResponseEntity.ok(projectMemberService.updateRole(projectId, userId, projectMemberRoleUpdateDTO.role()));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("@userSecurity.isAdmin(authentication)")
    public ResponseEntity<Void> removeMember(@PathVariable Long projectId, @PathVariable Long userId) {
        projectMemberService.removeMember(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}