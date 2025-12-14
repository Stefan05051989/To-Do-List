import { useState } from "react"
import Column from "../Cards.jsx/Column"

function ToDoList() {
  const [tasks, setTasks] = useState([])
  const [newTask, setNewTask] = useState("")

  function handleInputChange(e) {
    setNewTask(e.target.value) 
  }

  function addTask() {
    if (newTask.trim() !== "") {
      const task = {
        id: Date.now(),
        text: newTask,
        status: "created"
      }
      setTasks([...tasks, task])
      setNewTask("")
    }
  }

  // Mark as deleted (keeps in state for backend sync later)
  function deleteTask(id) {
    setTasks(tasks.map((task) => {
      if (task.id !== id) return task
      return { ...task, status: "deleted" }
    }))
  }

  function moveTask(id, targetStatus) {
    setTasks(tasks.map((task) => {
      if (task.id !== id) return task
      return { ...task, status: targetStatus }
    }))
  }

  // Filter tasks by status (deleted tasks are hidden)
  const createdTasks = tasks.filter((task) => task.status === "created")
  const inProgressTasks = tasks.filter((task) => task.status === "inprogress")
  const doneTasks = tasks.filter((task) => task.status === "done")
  const archivedTasks = tasks.filter((task) => task.status === "archived")
  // Deleted tasks stay in state but aren't displayed
  // const deletedTasks = tasks.filter((task) => task.status === "deleted")

  return (
    <div className="todo-container">
      <h1>To Do List</h1>

      <div className="todo-input">
        <input
          type="text"
          placeholder="Add task"
          value={newTask}
          onChange={handleInputChange}
        />
        <button className="add-button" onClick={addTask}>
          Add Task
        </button>
      </div>

      {/* 3 Columns */}
      <div className="board">
        <Column
          title="Created"
          status="created"
          tasks={createdTasks}
          onDelete={deleteTask}
          onMove={moveTask}
        />
        <Column
          title="In Progress"
          status="inprogress"
          tasks={inProgressTasks}
          onDelete={deleteTask}
          onMove={moveTask}
        />
        <Column
          title="Done"
          status="done"
          tasks={doneTasks}
          onDelete={deleteTask}
          onMove={moveTask}
        />
      </div>

      {/* Archived Banner */}
      <div className="archived-banner">
        <Column
          title="Archived"
          status="archived"
          tasks={archivedTasks}
          onDelete={deleteTask}
          onMove={moveTask}
        />
      </div>
    </div>
  )
}

export default ToDoList