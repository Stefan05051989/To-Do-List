package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    @JsonIgnore
    private TaskList taskList;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Embedded
    private DateInfo dateInfo =  new DateInfo();
    private int migrationCount = 0;

    public Task(){
        this.status = Status.CREATED;
    }
    public Long getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public TaskList getTaskList() {
        return taskList;
    }
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public DateInfo getDateInfo() {
        return dateInfo;
    }
    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }
    public int getMigrationCount() {
        return migrationCount;
    }
    public void setMigrationCount(int migrationCount) {
        this.migrationCount = migrationCount;
    }
}
