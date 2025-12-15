/*
Stefan Kiers
Model definitions
*/

// Status Enum 
export type Status = "CREATED" | "IN_PROGRESS" | "DONE" | "TESTING" | "ARCHIVED" | "MIGRATED";

//  User DTOs 
export interface UserCreateDTO {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
}

export interface UserUpdateDTO {
    firstName: string;
    lastName: string;
    email: string;
}

export interface UserSummaryDTO {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
}

export interface UserChangePasswordDTO {
    email: string;
    currentPassword: string;
    newPassword: string;
}

// TaskList DTOs
export interface TaskListCreateDTO {
    title: string;
    userId: number;
}

export interface TaskListSummaryDTO {
    id: number;
    title: string;
}

export interface TaskListUpdateDTO {
    title: string;
}

// Task 
export interface TaskCreateDTO {
    title: string;
    content: string;
    taskListId: number;
    status?: Status;  // Optioneel, default is CREATED
}

export interface TaskSummaryDTO {
    id: number;
    title: string;
    content: string;
    taskListId: number;
    status: Status;
}

export interface TaskUpdateDTO {
    title: string;
    content: string;
    status: Status;
}

export interface TaskListDetailProps {
    taskListId: number;
    setTaskListId: (id: number) => void;
}


export interface TaskCardProps {
    task: TaskSummaryDTO;
}
export interface ColumnProps {
    status: Status;
    title: string;
    tasks: TaskSummaryDTO[];
}

export interface BoardProps {
    taskListId: number;
    taskListTitle: string;
    onBack: () => void;
}