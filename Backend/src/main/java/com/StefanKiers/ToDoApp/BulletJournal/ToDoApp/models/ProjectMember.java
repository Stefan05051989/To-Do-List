package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * ProjectMember
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models
 *
 * @author Stefan Kiers
 * @since 5-9-2026
 */

@Entity
@Table(name = "project_member",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_id", "user_id"})
})

public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore // wel gebruikersdata, niet hele project meesturen.
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role;

    public ProjectMember() {}
    public ProjectMember(Project project, User user, Role role) {
        this.project = project;
        this.user = user;
        this.role = role;
    }
    // iedereen is standaard developer, dus default rol. (upgrade naar PO / SM wordt doorgevoerd door admin!
    public ProjectMember(Project project, User user) {
        this(project, user, Role.DEVELOPER);
    }

    public Long getId() {
        return id;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}
