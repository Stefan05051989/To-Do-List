package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.projectmember.ProjectMemberSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.ProjectMember;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProjectMemberMapper {
    public ProjectMemberSummaryDTO toProjectMemberSummaryDTO(ProjectMember projectMember) {
        return new ProjectMemberSummaryDTO(
                projectMember.getId(),
                projectMember.getUser().getId(),
                projectMember.getUser().getFirstName(),
                projectMember.getUser().getLastName(),
                projectMember.getRole()
        );
    }

    public List<ProjectMemberSummaryDTO> toProjectMemberSummaryDTO(List<ProjectMember> projectMembers) {
        return projectMembers.stream().map(this::toProjectMemberSummaryDTO).toList();
    }
}

