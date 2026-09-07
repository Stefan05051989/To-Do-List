package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.security;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.ProjectMember;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.ProjectMemberRepository;
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
    public UserSecurity(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }
    public boolean isAdmin(Authentication authentication) {
        User user = extractUser(authentication);
        System.out.println("DEBUG - UserSecurity.isAdmin aangeroepen. user null? " + (user == null)
                           + ", isAdmin: " + (user != null && user.isAdmin()));
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
        return projectMember.isPresent() && projectMember.get().getRole() ==  role;
    }

    private User extractUser(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return null;
        }
        return (User) authentication.getPrincipal();
    }

}
