package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.SprintStatus;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.SprintMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Project;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Sprint;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.ProjectRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.SprintRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SprintService
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services
 *
 * @author Stefan Kiers
 * @since 16-9-2026
 */

@Service
@Transactional
public class SprintService {
    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;
    private final SprintMapper sprintMapper;

    public SprintService(SprintRepository sprintRepository, ProjectRepository projectRepository, SprintMapper sprintMapper) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
        this.sprintMapper = sprintMapper;
    }
    public SprintSummaryDTO createSprint(SprintCreateDTO sprintCreateDTO) {
        Project project = projectRepository.findById(sprintCreateDTO.projectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        Sprint sprint = sprintMapper.toEntity(sprintCreateDTO);
        sprint.setProject(project);
        return sprintMapper.toSprintSummaryDTO(sprintRepository.save(sprint));
    }
    public List<SprintSummaryDTO> getSprintsProjectById(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found");
        }
        return sprintMapper.toSprintSummaryDTO(sprintRepository.findByProjectId(projectId));
    }
    public SprintSummaryDTO getSprintById(Long id) {
        Sprint sprint =  sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));
        return sprintMapper.toSprintSummaryDTO(sprint);
    }
    public SprintSummaryDTO updateSprint(Long id, SprintUpdateDTO sprintUpdateDTO) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));
        sprintMapper.updateEntity(sprint, sprintUpdateDTO);
        return sprintMapper.toSprintSummaryDTO(sprintRepository.save(sprint));
    }
    public SprintSummaryDTO cancelSprint(Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));
        if (sprint.getSprintStatus() == SprintStatus.COMPLETED){
            throw new IllegalStateException("A completed sprint can not be cancelled.");
        }
        if (sprint.getSprintStatus() == SprintStatus.CANCELLED){
            throw new IllegalStateException("Sprint is already cancelled");
        }
        sprint.setSprintStatus(SprintStatus.CANCELLED);
        return sprintMapper.toSprintSummaryDTO(sprintRepository.save(sprint));
    }
    public void deleteSprint(Long id) {
        if (!sprintRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sprint not found");
        }
        sprintRepository.deleteById(id);
    }
}
