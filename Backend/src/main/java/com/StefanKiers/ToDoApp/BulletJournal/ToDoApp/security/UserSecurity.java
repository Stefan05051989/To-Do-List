package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.ProjectMember;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Sprint;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.ProjectMemberRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.SprintRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * UserSecurity
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 */
@Component("userSecurity")
public class UserSecurity {
    private final ProjectMemberRepository projectMemberRepository;
    private final SprintRepository sprintRepository;

    public UserSecurity(ProjectMemberRepository projectMemberRepository, SprintRepository sprintRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.sprintRepository = sprintRepository;
    }

    public boolean isAdmin(Authentication authentication) {
        User user = extractUser(authentication);
        return user != null && user.isAdmin();
    }

    public boolean hasProjectRole(Authentication authentication, Long projectId, Role role) {
        User user = extractUser(authentication);
        if (user == null) {
            return false;
        }
        if (user.isAdmin()) {
            return true;
        }
        Optional<ProjectMember> projectMember = projectMemberRepository.findByProjectIdAndUserId(projectId, user.getId());
        return projectMember.isPresent() && projectMember.get().getRole() == role;
    }

    public boolean canManageProject(Authentication authentication, Long projectId) {
        return hasProjectRole(authentication, projectId, Role.SCRUM_MASTER)
               || hasProjectRole(authentication, projectId, Role.PRODUCT_OWNER);
    }

    public boolean hasSprintRole(Authentication authentication, Long sprintId, Role role) {
        User user = extractUser(authentication);
        if (user == null) {
            return false;
        }
        if (user.isAdmin()) {
            return true;
        }
        Optional<Sprint> sprint = sprintRepository.findById(sprintId);
        if (sprint.isEmpty()) {
            return false;
        }
        Long projectId = sprint.get().getProject().getId();
        return hasProjectRole(authentication, projectId, role);
    }

    public boolean canManageSprintById(Authentication authentication, Long sprintId) {
        return hasSprintRole(authentication, sprintId, Role.SCRUM_MASTER)
               || hasSprintRole(authentication, sprintId, Role.PRODUCT_OWNER);
    }

    private User extractUser(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return null;
        }
        return (User) authentication.getPrincipal();
    }
}