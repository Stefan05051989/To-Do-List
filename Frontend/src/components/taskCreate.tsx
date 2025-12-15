import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import type { TaskCreateDTO } from "../models.d.ts/models.tsx";
import { API_URL } from "../App";

interface TaskCreateProps {
    setPlacingTask: (id: number) => void;
    taskListId: number;
}

const TaskCreate = ({ setPlacingTask, taskListId }: TaskCreateProps) => {
    const [task, setState] = useState({ title: "", content: "" });
    const queryClient = useQueryClient();

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
            setState({ title: "", content: "" });
            queryClient.invalidateQueries({ queryKey: ["tasks", taskListId] });
            console.log(" task crwated succesfully");
            setPlacingTask(0);
        },
    });

    const handleSubmit = (event: React.FormEvent<HTMLElement>) => {
        event.preventDefault();
        createTask.mutate({
            title: task.title,
            content: task.content,
            taskListId: taskListId,
        });
        console.log("Task Submitted " + task.title);
    };

    const handleChange = (
        event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = event.target;
        setState({ ...task, [name]: value });
    };

    return (
        <>
            <h3>Nieuwe Task</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="title">Titel:</label>
                    <input
                        type="text"
                        id="title"
                        name="title"
                        value={task.title}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="content">Inhoud:</label>
                    <textarea
                        id="content"
                        name="content"
                        value={task.content}
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" disabled={createTask.isPending}>
                    Maak Task
                </button>
                <button onClick={() => setPlacingTask(0)}>terug</button>
            </form>
        </>
    );
};

export default TaskCreate;

