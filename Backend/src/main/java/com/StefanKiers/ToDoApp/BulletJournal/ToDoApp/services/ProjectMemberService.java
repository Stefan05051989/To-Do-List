package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember.ProjectMemberSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.ProjectMemberMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Project;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.ProjectMember;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.ProjectMemberRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.ProjectRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectMemberService
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services
 *
 * @author Stefan Kiers
 * @since 7-9-2026
 */
@Service
@Transactional
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberMapper projectMemberMapper;

    public ProjectMemberService(ProjectMemberRepository projectMemberRepository, ProjectRepository projectRepository,
                                UserRepository userRepository, ProjectMemberMapper projectMemberMapper) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMemberMapper = projectMemberMapper;
    }

    public ProjectMemberSummaryDTO addMember(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (projectMemberRepository.findByProjectIdAndUserId(projectId, userId).isPresent()) {
            throw new IllegalStateException("User is already a member of this project");
        }

        ProjectMember projectMember = new ProjectMember(project, user);
        return projectMemberMapper.toProjectMemberSummaryDTO(projectMemberRepository.save(projectMember));
    }

    public ProjectMemberSummaryDTO updateRole(Long projectId, Long userId, Role role) {
        ProjectMember projectMember = projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found"));

        projectMember.setRole(role);
        return projectMemberMapper.toProjectMemberSummaryDTO(projectMemberRepository.save(projectMember));
    }

    public List<ProjectMemberSummaryDTO> getByProjectId(Long projectId) {
        return projectMemberMapper.toProjectMemberSummaryDTO(projectMemberRepository.findByProjectId(projectId));
    }

    public void removeMember(Long projectId, Long userId) {
        ProjectMember projectMember = projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found"));
        projectMemberRepository.delete(projectMember);
    }
}