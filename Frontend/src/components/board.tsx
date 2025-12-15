import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { API_URL } from "../App";
import type { TaskSummaryDTO, TaskCreateDTO, TaskUpdateDTO, Status } from "../types/models.d";
import {DndContext,type DragEndEvent ,DragOverlay, type DragStartEvent, PointerSensor, useSensor, useSensors, closestCorners} from "@dnd-kit/core";
import Column from "../components/TaskColumnDroppable";
import TaskCard from "./TaskCard";
import type { BoardProps } from "../types/models.d"

const COLUMNS: { status: Status; title: string }[] = [
    { status: "CREATED", title: "Created" },
    { status: "IN_PROGRESS", title: "In Progress" },
    { status: "DONE", title: "Done" },
    { status: "ARCHIVED", title: "Archived" },
];

const Board = ({ taskListId, taskListTitle, onBack }: BoardProps) => {
    const [newTaskTitle, setNewTaskTitle] = useState("");
    const [activeTask, setActiveTask] = useState<TaskSummaryDTO | null>(null);
    const queryClient = useQueryClient();

    const sensors = useSensors(
        useSensor(PointerSensor, {
            activationConstraint: {
                distance: 8,
            },
        })
    );

    // Fetch tasks
    const { data: tasks, isLoading } = useQuery<TaskSummaryDTO[]>({
        queryKey: ["tasks", taskListId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/task/tasklist/${taskListId}`);
            if (!response.ok) throw new Error("task error");
            return response.json();
        },
    });

    // Create task
    const createTask = useMutation({
        mutationFn: async (task: TaskCreateDTO) => {
            const response = await fetch(`${API_URL}/task`, {
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

   // Update task (voor drag-and-drop)
const updateTask = useMutation({
    mutationFn: async ({ taskId, data }: { taskId: number; data: TaskUpdateDTO }) => {
        const response = await fetch(`${API_URL}/task/${taskId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });
        if (!response.ok) throw new Error("failed to update task");
        return response.json();
    },
    onSuccess: async () => {
        console.log("Mutation success - refetching...");  // Debug
        await queryClient.refetchQueries({ queryKey: ["tasks", taskListId] });
        console.log("Refetch complete");  // Debug
    },
    });

    // Delete tasklist
    const deleteTaskList = useMutation({
        mutationFn: async () => {
            const response = await fetch(`${API_URL}/tasklists/${taskListId}`, {
                method: "DELETE",
            });
            if (!response.ok) throw new Error("Failed to delete tasklist.");
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["taskLists"] });
            if (onBack) onBack();
        },
    });

    const handleAddTask = (e: React.FormEvent) => {
        e.preventDefault();
        if (!newTaskTitle.trim()) return;
        createTask.mutate({
            title: newTaskTitle,
            content: "",
            taskListId: taskListId,
            status: "CREATED",
        });
    };

    const filterByStatus = (status: Status) => {
        return tasks?.filter((task) => task.status === status) || [];
    };

    // Drag handlers
    const handleDragStart = (event: DragStartEvent) => {
        const task = tasks?.find((t) => t.id === event.active.id);
        if (task) setActiveTask(task);
    };

    const handleDragEnd = (event: DragEndEvent) => {
    const { active, over } = event;
    
    console.log("Drag ended:", { active: active.id, over: over?.id });
    
    setActiveTask(null);

    if (!over) {
        console.log("No drop target detected");
        return;
    }

    const taskId = active.id as number;
    const overId = over.id;

    // Check of over.id een kolom status is
    const isColumn = COLUMNS.some((col) => col.status === overId);
    
    let newStatus: Status;
    
    if (isColumn) {
        // Gedropt op een kolom
        newStatus = overId as Status;
    } else {
        // Gedropt op een andere task - zoek de status van die task
        const targetTask = tasks?.find((t) => t.id === overId);
        if (!targetTask) {
            console.log("Target task not found");
            return;
        }
        newStatus = targetTask.status;
    }
    // check waarom status niet geupdate wordt
    console.log("Task ID:", taskId, "New Status:", newStatus);

    const task = tasks?.find((t) => t.id === taskId);
    if (!task || task.status === newStatus) {
        console.log("Task not found or same status");
        return;
    }

    console.log("Updating task to:", newStatus);
    
    updateTask.mutate({
        taskId,
        data: {
            title: task.title,
            content: task.content,
            status: newStatus,
        },
    });

};
    if (isLoading) return <div>Loading...</div>;

    return (
        <div className="board-container">
            <div className="board-header">
                <div style={{ display: "flex", justifyContent: "space-between", marginBottom: "10px" }}>
                    <button className="back-button" onClick={onBack}>
                        ← Terug
                    </button>
                    <button
                        className="columnButtonDelete"
                        onClick={() => {
                            if (confirm("Delete tasklist?")) {
                                deleteTaskList.mutate();
                            }
                        }}
                    >
                        Delete list
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
                <button type="submit" className="add-task-button">
                    Add Task
                </button>
            </form>

            <DndContext
                sensors={sensors}
                collisionDetection={closestCorners}
                onDragStart={handleDragStart}
                onDragEnd={handleDragEnd}
                
            >
                <div className="board">
                    {COLUMNS.map((column) => (
                        <Column
                            key={column.status}
                            status={column.status}
                            title={column.title}
                            tasks={filterByStatus(column.status)}
                        />
                    ))}
                </div>

                <DragOverlay>
                    {activeTask ? <TaskCard task={activeTask} /> : null}
                </DragOverlay>
            </DndContext>
        </div>
    );
};

export default Board;