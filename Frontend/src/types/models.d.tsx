/*
Stefan Kiers
Model definitions.
*/

import type { TaskStatusType } from "./types.d";

export interface Task{
    id: number;
    projectId: number;
    title: string;
    content: string;
    status : TaskStatusType;
    createdAt: string;
    updatedAt: string;
}

export interface Project{
    id: number;
    title: string;
    content: string;
    createdAt: string;
    updatedAt: string;
}

export interface User{
    id: number;
    name: string;
}

// export interface ToDoUserCreateDTO {
//     firstName: string;
//     lastName: string;
//     email: string;
//     password: string;
// }

// export interface ToDoUserUpdateDTO {
//     firstName: string;
//     lastName: string;
//     email: string;
// }

// export interface ToDoUserSummaryDTO {
//     id: number;
//     firstName: string;
//     lastName: string;
//     email: string;
// }

// export interface ToDoUserChangePasswordDTO {
//     email: string;
//     currentPassword: string;
//     newPassword: string;
// }

// // ------------- TaskList -----------------
// export interface TaskListCreateDTO {
//     title: string;
//     userId: number;
// }

// export interface TaskListSummaryDTO {
//     id: number;
//     title: string;
// }

// export interface TaskListUpdateDTO {
//     title: string;
// }

// // ------------- Task -----------------
// export interface TaskCreateDTO {
//     title: string;
//     content: string;
//     taskListId: number;
// }

// export interface TaskSummaryDTO {
//     id: number;
//     title: string;
//     content: string;
//     taskListId: number;
// }

// export interface TaskUpdateDTO {
//     title: string;
//     content: string;
// }

// // ------------- Comment -----------------
// export interface CommentCreateDTO {
//     content: string;
//     taskId: number;
//     userId: number;
// }

// export interface CommentSummaryDTO {
//     id: number;
//     content: string;
//     taskId: number;
//     userId: number;
// }

// export interface CommentUpdateDTO {
//     content: string;
// }

// // ------------- enums -----------------
// export type Status = "CREATED" | "IN_PROGRESS" | "DONE" | "TESTING" | "ARCHIVED" | "MIGRATED";
