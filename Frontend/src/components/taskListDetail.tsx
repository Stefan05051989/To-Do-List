import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../App";
import type { TaskListSummaryDTO, TaskSummaryDTO } from "../types/models";
import { Fragment, useState } from "react";
import TaskCreate from "./taskCreate";
import { useUser } from "../stores/userStore";

interface TaskListDetailProps {
    taskListId: number;
    setTaskListId: (id: number) => void;
}

const TaskListDetail = ({ taskListId, setTaskListId }: TaskListDetailProps) => {
    const [placingTask, setPlacingTask] = useState<number>(0);
    const user = useUser();

    // Fetch TaskList details
    const {
        data: taskList,
        isLoading: taskListLoading,
        error: taskListError,
    } = useQuery<TaskListSummaryDTO>({
        queryKey: ["taskList", taskListId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/tasklist/${taskListId}`);
            if (!response.ok) {
                throw new Error("tasklist error");
            }
            return response.json();
        },
    });

    // Fetch Tasks for this TaskList
    const {
        data: tasks,
        isLoading: tasksLoading,
        error: tasksError,
    } = useQuery<TaskSummaryDTO[]>({
        queryKey: ["tasks", taskListId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/tasks/tasklist/${taskListId}`);
            if (!response.ok) {
                throw new Error("tasks error");
            }
            return response.json();
        },
    });

    if (taskListError || tasksError) {
        return (
            <div style={{ color: "red" }}>
                Error: {taskListError?.message || tasksError?.message}
            </div>
        );
    }

    if (taskListLoading || tasksLoading || taskList == undefined) {
        return <div>Loading...</div>;
    }

    if (placingTask > 0) {
        return <TaskCreate setPlacingTask={setPlacingTask} taskListId={taskListId} />;
    }

    return (
        <>
            <h3>TaskList detail {taskList.id}</h3>
            <h4>titel: {taskList.title}</h4>
            <br />
            <h4>Tasks:</h4>
            <ul>
                {tasks && tasks.length > 0 ? (
                    tasks.map((task: TaskSummaryDTO) => (
                        <Fragment key={task.id}>
                            <li>
                                <p>
                                    <strong>{task.title}</strong>
                                </p>
                                <p>{task.content}</p>
                            </li>
                            <br />
                        </Fragment>
                    ))
                ) : (
                    <p>Geen tasks gevonden</p>
                )}
            </ul>
            <br />
            {user.id > 0 && (
                <button onClick={() => setPlacingTask(1)}>Nieuwe Task</button>
            )}
            <button onClick={() => setTaskListId(NaN)}>terug</button>
        </>
    );
};

export default TaskListDetail;
