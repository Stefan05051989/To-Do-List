package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Status;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskListId(Long taskListId);
    List<Task> findByUserId(Long userId);  // alle taken per user
    List<Task> findByStatus(Status status);  // alle taken per status
    List<Task> findByTaskListIdAndStatus(Long taskListId, Status status);
}

