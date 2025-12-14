// mapper/TaskListMapper.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.mapper;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.tasklist.TaskListUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.TaskList;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskListMapper {

    public TaskList toEntity(TaskListCreateDTO dto, User user) {
        TaskList taskList = new TaskList();
        taskList.setTitle(dto.title());
        taskList.setUser(user);
        return taskList;
    }

    public void updateEntity(TaskList taskList, TaskListUpdateDTO dto) {
        taskList.setTitle(dto.title());
    }

    public TaskListSummaryDTO toTaskListSummaryDTO(TaskList taskList) {
        return new TaskListSummaryDTO(
                taskList.getId(),
                taskList.getTitle()
        );
    }

    public List<TaskListSummaryDTO> toTaskListSummaryDTOList(List<TaskList> taskLists) {
        return taskLists.stream()
                .map(this::toTaskListSummaryDTO)
                .toList();
    }
}