import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { API_URL } from "../App";
import type { TaskListSummaryDTO } from "../types/models.d";
import Board from "../components/board";
import { useUser } from "../stores/userStore";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import TaskListCreate from "../components/taskListCreate";

const TaskListPage = () => {
    const [selectedTaskListId, setSelectedTaskListId] = useState<number | null>(null);
    const navigate = useNavigate();
    const user = useUser();

    // navigate to homepage
    useEffect(() => {
        if (isNaN(user.id)) {
            navigate("/");
        }
    }, [user.id, navigate]);

    // TASKLISTS, NIET TASKLIST
    const { data: taskLists, isLoading, error } = useQuery<TaskListSummaryDTO[]>({
        queryKey: ["taskLists", user.id],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/tasklists/user/${user.id}`);
            if (!response.ok) throw new Error("tasklist error");
            return response.json();
        },
        enabled: !isNaN(user.id),
    });

    if (isLoading) return <div className="loading">Loading...</div>;
    if (error) return <div className="error">Error: {error.message}</div>;


    if (selectedTaskListId) {
        const selected = taskLists?.find((taskList) => taskList.id === selectedTaskListId);
        return (
            <Board
                taskListId={selectedTaskListId}
                taskListTitle={selected?.title || "To Do List"}
                onBack={() => setSelectedTaskListId(null)}
            />
        );
    }

    return (
        <>
        <TaskListCreate />
        <div className="tasklist-page">
            <h1>Mijn To-Do's</h1>
            <div className="tasklist-grid">
                {taskLists && taskLists.length > 0 ? (
                    taskLists.map((taskList) => (
                        <div
                            key={taskList.id}
                            className="tasklist-card"
                            onClick={() => setSelectedTaskListId(taskList.id)}
                        >
                            <h3>{taskList.title}</h3>
                            <p>Klik om te openen</p>
                        </div>
                    ))
                ) : (
                    <p>Geen lijsten gevonden</p>
                )}
            </div>
        </div>
        </>
    );
};

export default TaskListPage;