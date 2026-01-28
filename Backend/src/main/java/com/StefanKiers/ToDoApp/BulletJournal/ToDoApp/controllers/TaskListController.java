// controllers/TaskListController.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tasklists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskListSummaryDTO> create(@RequestBody TaskListCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskListService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskListSummaryDTO>> getAll() {
        return ResponseEntity.ok(taskListService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListSummaryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskListService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskListSummaryDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskListService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListSummaryDTO> update(@PathVariable Long id, @RequestBody TaskListUpdateDTO dto) {
        return ResponseEntity.ok(taskListService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskListService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
//```
//
//        ---
//
//        ## 📋 Samenvatting TaskList
//
//| Bestand | Methodes/Velden |
//        |---------|-----------------|
//        | **TaskList entity** | id, title, user |
//        | **TaskListCreateDTO** | title, userId |
//        | **TaskListSummaryDTO** | id, title |
//        | **TaskListUpdateDTO** | title |
//        | **TaskListMapper** | `toEntity()`, `updateEntity()`, `toTaskListSummaryDTO()`, `toTaskListSummaryDTOList()` |
//        | **TaskListService** | `create()`, `getAll()`, `getById()`, `getByUserId()`, `updateTask()`, `delete()` |
//        | **TaskListController** | POST, GET, GET/{id}, GET/user/{userId}, PUT/{id}, DELETE/{id} |
//
//        ---
//
//        ## 🗑️ Te verwijderen bestanden
//```
//dto/tasklist/TaskListDTO.java       ← ongebruikt
//dto/tasklist/TaskListDeleteDTO.java ← onnodig