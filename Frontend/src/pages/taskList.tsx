import { useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { API_URL } from "../App";
import type { TaskListSummaryDTO } from "../types/models.d";
import KanbanBoard from "../components/board";
import { useUser } from "../stores/userStore";
import { useNavigate } from "react-router-dom";

const TaskListPage = () => {
    const [selectedTaskListId, setSelectedTaskListId] = useState<number | null>(null);

    const navigate = useNavigate();

    const user = useUser();
    if (isNaN(user.id)) {
        navigate("/");
    }

    const { data: taskLists, isLoading, error } = useQuery({
        queryKey: ["taskLists", user.id],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/tasklist/user/${user.id}`);
            if (!response.ok) throw new Error("tasklist error");
            return response.json();
        },
    });

    if (isLoading) return <div className="loading">Loading...</div>;
    if (error) return <div className="error">Error: {error.message}</div>;

    if (selectedTaskListId) {
        const selected = taskLists?.find((tl: TaskListSummaryDTO) => tl.id === selectedTaskListId);
        return (
            <KanbanBoard
                taskListId={selectedTaskListId}
                taskListTitle={selected?.title || "To Do List"}
                // onBack={() => setSelectedTaskListId(null)}
            />
        );
    }

    return (
        <div className="tasklist-page">
            <h1>Mijn Lijsten</h1>
            <div className="tasklist-grid">
                {taskLists && taskLists.length > 0 ? (
                    taskLists.map((taskList: TaskListSummaryDTO) => (
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
    );
};

export default TaskListPage;