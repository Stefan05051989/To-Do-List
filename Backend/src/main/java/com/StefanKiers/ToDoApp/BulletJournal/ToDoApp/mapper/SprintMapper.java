package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.sprint.SprintUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Sprint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SprintMapper
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper
 *
 * @author Stefan Kiers
 * @since 9-9-2026
 */

@Component
public class SprintMapper {

    public Sprint toEntity(SprintCreateDTO sprintCreateDTO){
        Sprint sprint = new Sprint();
        sprint.setTitle(sprintCreateDTO.title());
        sprint.setDescription(sprintCreateDTO.description());
        sprint.setStartDate(sprintCreateDTO.startDate());
        sprint.setEndDate(sprintCreateDTO.endDate());
        return sprint;
    }
    public void updateEntity(Sprint sprint, SprintUpdateDTO  sprintUpdateDTO){
        sprint.setTitle(sprintUpdateDTO.title());
        sprint.setDescription(sprintUpdateDTO.description());
        sprint.setStartDate(sprintUpdateDTO.startDate());
        sprint.setEndDate(sprintUpdateDTO.endDate());
    }
    public SprintSummaryDTO toSprintSummaryDTO(Sprint sprint){
        return new SprintSummaryDTO(
                sprint.getId(),
                sprint.getTitle(),
                sprint.getDescription(),
                sprint.getStartDate(),
                sprint.getEndDate(),
                sprint.getSprintStatus(),
                sprint.getProject().getId()
        );
    }
    public List<SprintSummaryDTO> toSprintSummaryDTO(List<Sprint> sprints){
        return sprints.stream().map(this::toSprintSummaryDTO).toList();
    }
}
