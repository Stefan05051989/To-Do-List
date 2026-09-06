package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.ProjectMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Project;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectService
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 */

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectSummaryDTO createProject(ProjectCreateDTO projectCreateDTO) {
        Project project = projectMapper.toEntity(projectCreateDTO);
        return projectMapper.toProjectSummaryDTO(projectRepository.save(project));
    }

    public List<ProjectSummaryDTO> getAllProjects() {
        return projectMapper.toProjectSummaryDTO(projectRepository.findAll());
    }

    public ProjectSummaryDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return projectMapper.toProjectSummaryDTO(project);
    }
    public ProjectSummaryDTO updateProject(Long id, ProjectUpdateDTO projectUpdateDTO) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Project not found"));
        projectMapper.updateEntity(project, projectUpdateDTO);
        return projectMapper.toProjectSummaryDTO(projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)){
            throw new ResourceNotFoundException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
