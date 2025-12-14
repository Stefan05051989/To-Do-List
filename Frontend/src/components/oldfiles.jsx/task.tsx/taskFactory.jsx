import { TaskStatus } from "./taskConfic";

/*
Stefan Kiers
06-12-'25
function for creating a task with props id (current date) text, and status passed from taskstatus. 
*/


export function createTask(text){
    return {
        id: Date.now,
        text: text,
        status: TaskStatus.CREATED // <- default can be created, cause you don't make a task for delete..
    }
}