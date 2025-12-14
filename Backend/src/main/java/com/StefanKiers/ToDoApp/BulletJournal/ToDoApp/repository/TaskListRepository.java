package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.TaskList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByUserId(Long userId);
}
