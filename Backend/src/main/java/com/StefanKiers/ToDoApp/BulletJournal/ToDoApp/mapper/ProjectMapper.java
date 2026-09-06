package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.project.ProjectUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Project;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProjectMapper
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 */

@Component
public class ProjectMapper {
    public Project toEntity(ProjectCreateDTO projectCreateDTO) {
        Project project = new Project();
        project.setTitle(projectCreateDTO.title());
        project.setDeadline(projectCreateDTO.deadLine());
        return project;
    }
    public void updateEntity(Project project, ProjectUpdateDTO projectUpdateDTO) {
        project.setTitle(projectUpdateDTO.title());
        project.setDeadline(projectUpdateDTO.deadLine());
    }
    public ProjectSummaryDTO toProjectSummaryDTO(Project project) {
        return new ProjectSummaryDTO(
                project.getId(),
                project.getTitle(),
                project.getDeadline()
        );
    }
    public List<ProjectSummaryDTO> toProjectSummaryDTO(List<Project> projects) {
        return projects.stream().map(this::toProjectSummaryDTO).toList();
    }
}
