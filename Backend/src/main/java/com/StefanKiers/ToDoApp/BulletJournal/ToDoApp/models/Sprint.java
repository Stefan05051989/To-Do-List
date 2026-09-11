package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.SprintStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Sprint
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models
 *
 * @author Stefan Kiers
 * @since 9-9-2026
 */

@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;
    @Column(nullable = false, length = 300)
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SprintStatus sprintStatus;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    public Sprint(){
        this.sprintStatus = sprintStatus.PLANNED;
    }
    public Sprint(String title, String description, LocalDate startDate, LocalDate endDate, Project project){
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
        this.sprintStatus = SprintStatus.PLANNED;
    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }
    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

}
