import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { API_URL } from "../App";

const TaskUpdate = ({ setEditTask, taskId }) => {

    const [task, setState] = useState({ Name : '', Content : '' });
    const queryClient = useQueryClient();

    const updateTask = useMutation({
        mutationFn: async (task) => {
            const response = await fetch(`${API_URL}/tasks/${taskId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(task)
            });
            if (!response.ok) throw new Error('Failed to update Task, Try again.');
            return response.json();
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['task', taskId] });
            queryClient.invalidateQueries({ queryKey: ['tasks'] });
            setEditTask(null);
            console.log("Tasks edited succesfully.");
        }
    });

    const handleSubmit = (event) => {
        event.preventDefault();
        updateTask.mutate(task);
        console.log("Task Submitted " + task.Name);
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setState({ ...task, [name]: value });
    };

    return (
        <>
            <h3>Edit Task : </h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">Title :</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={task.Name}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="content">Content :</label>
                    <textarea
                        id="content"
                        name="content"
                        value={task.Content}
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" disabled={updateTask.isPending}>Change Task : </button>
                <button onClick={() => setEditTask(null)}>Accept.</button>
            </form>
        </>
    );
};

export default TaskUpdate;
