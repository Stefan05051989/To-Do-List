import { useMutation, useQueryClient } from "@tanstack/react-query";
import React, { useState } from "react";
import type { TaskListCreateDTO } from "../types/models.d";
import { API_URL } from "../App";
import  { useUser }  from "../stores/userStore";

const TaskListCreate = () => {
    const user = useUser();

    const [taskList, setState] = useState({ title: "" });
    const queryClient = useQueryClient();

    const createTaskList = useMutation({
        mutationFn: async (taskList: TaskListCreateDTO) => {
            const data = { title: taskList.title, userId: user.id};

            const response = await fetch(`${API_URL}/tasklists`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data),
            });
            if (!response.ok) throw new Error("failed to create tasklist");
            return response.json();
        },
        onSuccess: () => {
            setState({ title: "" });
            queryClient.invalidateQueries({ queryKey: ["taskLists"] });
            console.log("succes");
        },
    });

    const handleSubmit = (event: React.FormEvent<HTMLElement>) => {
        event.preventDefault();
        createTaskList.mutate({ title: taskList.title, userId: user.id });
        console.log("Submitted " + taskList.title);
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setState({ ...taskList, [name]: value });
    };

    return (
        <>
            <h3>Maak een TaskList</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="title">Titel:</label>
                    <input
                        type="text"
                        id="title"
                        name="title"
                        value={taskList.title}
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" disabled={createTaskList.isPending}>
                    Add TaskList
                </button>
            </form>
        </>
    );
};

export default TaskListCreate;
