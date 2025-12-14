// services/TaskListService.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper.TaskListMapper;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.TaskList;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.TaskListRepository;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;
    private final TaskListMapper taskListMapper;

    public TaskListService(TaskListRepository taskListRepository,
                           UserRepository userRepository,
                           TaskListMapper taskListMapper) {
        this.taskListRepository = taskListRepository;
        this.userRepository = userRepository;
        this.taskListMapper = taskListMapper;
    }

    public TaskListSummaryDTO create(TaskListCreateDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        TaskList taskList = taskListMapper.toEntity(dto, user);
        return taskListMapper.toTaskListSummaryDTO(taskListRepository.save(taskList));
    }

    public List<TaskListSummaryDTO> getAll() {
        return taskListMapper.toTaskListSummaryDTOList(taskListRepository.findAll());
    }

    public TaskListSummaryDTO getById(Long id) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found"));
        return taskListMapper.toTaskListSummaryDTO(taskList);
    }

    public List<TaskListSummaryDTO> getByUserId(Long userId) {
        return taskListMapper.toTaskListSummaryDTOList(taskListRepository.findByUserId(userId));
    }

    public TaskListSummaryDTO update(Long id, TaskListUpdateDTO dto) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskList not found"));
        taskListMapper.updateEntity(taskList, dto);
        return taskListMapper.toTaskListSummaryDTO(taskListRepository.save(taskList));
    }

    public void delete(Long id) {
        if (!taskListRepository.existsById(id)) {
            throw new ResourceNotFoundException("TaskList not found");
        }
        taskListRepository.deleteById(id);
    }
}