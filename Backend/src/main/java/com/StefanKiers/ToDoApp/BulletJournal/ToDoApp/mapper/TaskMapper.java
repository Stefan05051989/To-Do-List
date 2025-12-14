// mapper/TaskMapper.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.task.TaskUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.Status;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public Task toEntity(TaskCreateDTO dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setContent(dto.content());
        task.setTaskListId(dto.taskListId());
        task.setStatus(dto.status() != null ? dto.status() : Status.CREATED);
        return task;
    }

    public void updateEntity(Task task, TaskUpdateDTO dto) {
        task.setTitle(dto.title());
        task.setContent(dto.content());
        if (dto.status() != null) {
            task.setStatus(dto.status());
        }
    }

    public TaskSummaryDTO toTaskSummaryDTO(Task task) {
        return new TaskSummaryDTO(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getTaskListId(),
                task.getStatus()
        );
    }

    public List<TaskSummaryDTO> toTaskSummaryDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toTaskSummaryDTO)
                .toList();
    }
}