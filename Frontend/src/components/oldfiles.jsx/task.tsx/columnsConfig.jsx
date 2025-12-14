import { TaskStatus } from "./taskConfic";

// column config for task titles

export const columnsConfig = [
        { status: TaskStatus.CREATED, title: "Created"},
        { status: TaskStatus.INPROGRESS, label: "In Progress"},
        { status: TaskStatus.DONE, label: "Done"},
]