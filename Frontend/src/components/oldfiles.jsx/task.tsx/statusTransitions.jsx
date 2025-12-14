import { TaskStatus } from "./taskConfic"; 
/*
Stefan Kiers
 filefor creating statusus in the to do list, Rendered from TaskConfig
 */

export const statusTransitions = {
     [TaskStatus.CREATED]: [
        { value: TaskStatus.INPROGRESS, label: "In Progress"},
        { value: TaskStatus.ARCHIVED, label: "Archive"},
        { value: TaskStatus.DELETED, label: "Delete"},
     ],
     [TaskStatus.INPROGRESS]: [
        { value: TaskStatus.CREATED, label: "Created"},
        { value: TaskStatus.DONE, label: "Done"},
        { value: TaskStatus.ARCHIVED, label: "Archive"},
        { value: TaskStatus.DELETED, label: "Delete"},
     ],[TaskStatus.DONE]: [
        { value: TaskStatus.INPROGRESS, label: "In Progress"},
        { value: TaskStatus.ARCHIVED, label: "Archive"},
        { value: TaskStatus.DELETED, label: "Delete"},
     ],[TaskStatus.CREATED]: [
        { value: TaskStatus.CREATED, label: "Created"},
        { value: TaskStatus.INPROGRESS, label: "In Progress"},
        { value: TaskStatus.DELETED, label: "Delete"},
     ],
}