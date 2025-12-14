import { useMutation, useQueryClient } from "@tanstack/react-query";
import React, { useState } from "react";
import { API_URL } from "../App";
import { useUser } from "../stores/userStore";

const TaskCreate = () => {

    const [topic, setState] = useState({ Title : '', content : '' });
    const queryClient = useQueryClient();
    const user = useUser();

    const createTask = useMutation({
        mutationFn: async (task) => {
            const response = await fetch(`${API_URL}/task/${user.id}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(task)
            });
            if (!response.ok) throw new Error('Task create failed, try again.');
            return response.json();
        },
        onSuccess: () => {
            setState({ Title : '', Content : '' });
            queryClient.invalidateQueries({ queryKey: ['tasks'] });
            console.log("Task created succesfully.");
        }
    });

    const handleSubmit = (event) => {
        event.preventDefault();
        createTask.mutate(task);
        console.log("Task submitted " + task.name);
    };

    const handleChange = (event) => {
        const { title, value } = event.target;
        setState({ ...task, [name]: value });
    };

    return (
        <>
            <h3>maak een topic</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">Onderwerp:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={topic.name}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label htmlFor="contents">Inhoud:</label>
                    <textarea
                        id="contents"
                        name="contents"
                        value={topic.contents}
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" disabled={createTopic.isPending}>Add Topic</button>
            </form>
        </>
    );
};