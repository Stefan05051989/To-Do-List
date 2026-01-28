/*
Stefan Kiers
*/

import type { Status } from "./models.d";

// types for status
export type TaskStatusType = "created" | "inProgress" | "done" | "archived" | "deleted";

// filter type : 
export type FilterType = "all" | "done";

export type Task ={
    id: number;
    title: string;
    content?: string;
    status: Status;
    taskListId: number;
}

export type TaskCardProps = {
    task: {
        task: Task,
        id: string | number; 
        title: string; 
        content?: string};
        onClick: (task: unknown) => void;
        onEdit?: (task: Task) => void;
        onDelete?: (taskId: number) => void;
        onStatusChange?: (taskId: number, completed: boolean) => void;
}

