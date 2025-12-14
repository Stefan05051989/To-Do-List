import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { API_URL } from "../App";
import type { TaskSummaryDTO, TaskCreateDTO } from "../types/models.d";

interface BoardProps {
    taskListId: number;
    taskListTitle: string;
    // onBack: () => void;
}

const Board = ({ taskListId, taskListTitle }: BoardProps) => { // onBack <- prop later toevoegen
    const [newTaskTitle, setNewTaskTitle] = useState("");
    const queryClient = useQueryClient();

    const { data: tasks, isLoading } = useQuery<TaskSummaryDTO[]>({
        queryKey: ["tasks", taskListId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/tasks/tasklist/${taskListId}`);
            if (!response.ok) throw new Error("tasks error");
            return response.json();
        },
    });

    const createTask = useMutation({
        mutationFn: async (task: TaskCreateDTO) => {
            const response = await fetch(`${API_URL}/tasks/new-task`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(task),
            });
            if (!response.ok) throw new Error("failed to create task");
            return response.json();
        },
        onSuccess: () => {
            setNewTaskTitle("");
            queryClient.invalidateQueries({ queryKey: ["tasks", taskListId] });
        },
    });

    // delete task werkt niet BOVEN create task...???
    const deleteTaskList = useMutation ({
        mutationFn: async () => {
            const response = await fetch(`$"{API_URL}/taskList/&{ taskListId}` ,{
                // method: DELETE,
            });
            if(!response.ok) throw new Error ("Failed to delete tasklist.");
        }, 
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: ["taskList"]
            });
           //  onBack(); method for going back to page after closing or deleting tasklist.
        }
    })

    const handleAddTask = (e: React.FormEvent) => {
        e.preventDefault();
        if (!newTaskTitle.trim()) return;
        createTask.mutate({
            title: newTaskTitle,
            content: "",
            taskListId: taskListId,
        });
    };

    if (isLoading) return <div>Loading...</div>;

    return (
        <div className="board-container">
            <div className="board-header">
                <div style={{display: "flex", justifyContent: "space-between", marginBottom: "10px" // <- px moert tussen double qoutes.
                }}>
                    <button className="columnButtonDelete" onClick={() => {
                        if(confirm("Delete tasklist?")){
                            deleteTaskList.mutate();
                        }}}> Delete list.
                    </button>
                </div>
                <h1>{taskListTitle}</h1>
            </div>

            <form className="add-task-form" onSubmit={handleAddTask}>
                <input
                    type="text"
                    placeholder="Add task"
                    value={newTaskTitle}
                    onChange={(e) => setNewTaskTitle(e.target.value)}
                />
                <button type="submit" className="add-task-button">Add Task</button>
            </form>

            <div className="board">
                <div className="board-column">
                    <h2>Created</h2>
                    <div className="board-tasks">
                        {tasks && tasks.length > 0 ? (
                            tasks.map((task) => (
                                <div key={task.id} className="task-card">
                                    <p>{task.title}</p>
                                </div>
                            ))
                        ) : (
                            <p className="no-tasks">No Tasks</p>
                        )}
                    </div>
                </div>

                <div className="board-column">
                    <h2>In Progress</h2>
                    <div className="boarrd-tasks">
                        <p className="no-tasks">No Tasks</p>
                    </div>
                </div>

                <div className="board-column">
                    <h2>Done</h2>
                    <div className="board-tasks">
                        <p className="no-tasks">No Tasks</p>
                    </div>
                </div>
            </div>

            <div className="board-column archived-column">
                <h2>Archived</h2>
                <div className="kanban-tasks">
                    <p className="no-tasks">No Tasks</p>
                </div>
            </div>
        </div>
    );
};

export default Board;