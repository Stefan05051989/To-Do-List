// controllers/TaskController.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskSummaryDTO> create(@RequestBody TaskCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskSummaryDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSummaryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @GetMapping("/tasklist/{taskListId}")
    public ResponseEntity<List<TaskSummaryDTO>> getByTaskListId(@PathVariable Long taskListId) {
        return ResponseEntity.ok(taskService.getByTaskListId(taskListId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskSummaryDTO> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
