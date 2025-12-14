// services/TaskService.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.TaskMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Task;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskSummaryDTO create(TaskCreateDTO dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toTaskSummaryDTO(taskRepository.save(task));
    }

    public List<TaskSummaryDTO> getAll() {
        return taskMapper.toTaskSummaryDTOList(taskRepository.findAll());
    }

    public TaskSummaryDTO getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return taskMapper.toTaskSummaryDTO(task);
    }

    public List<TaskSummaryDTO> getByTaskListId(Long taskListId) {
        return taskMapper.toTaskSummaryDTOList(taskRepository.findByTaskListId(taskListId));
    }

    public TaskSummaryDTO update(Long id, TaskUpdateDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskMapper.updateEntity(task, dto);
        return taskMapper.toTaskSummaryDTO(taskRepository.save(task));
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}