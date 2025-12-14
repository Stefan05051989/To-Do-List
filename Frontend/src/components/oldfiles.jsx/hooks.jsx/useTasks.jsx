import { useState } from "react";
import { TaskStatus } from "../utils.jsx/taskConfic"
import { createTask } from "../utils.jsx/taskFactory"

export function useTasks(){
    const[tasks, setTasks] = useState([])

    function addTask(text){
        if(text === "")
            return false;
        
        const newTask = createTask(text)
        setTasks(previousTasks => [...previousTasks, newTask])
        return true;
    }

    function deleteTask(id) {
    setTasks(previousTasks =>
      previousTasks.map(task =>
        task.id === id
          ? { ...task, status: TaskStatus.DELETED }
          : task
      )
    )
    }
    function moveTask(id, targetStatus) { // id _ target status, target status verwijst naar object ...task -> status
    setTasks(previousTasks =>
      previousTasks.map(task =>
        task.id === id
          ? { ...task, status: targetStatus }
          : task
      )
    )
    }

    function getTasksByStatus(status){
        return tasks.filter(task => task.status === status)
    
    }

    return {
        tasks, addTask, deleteTask, moveTask, getTasksByStatus
    }
    
}

