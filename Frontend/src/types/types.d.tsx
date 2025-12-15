/*
Stefan Kiers
Type definitions

Types and models can't be in the same files cause then they don't render properly.
*/ 

// types for status
export type TaskStatusType = "created" | "inProgress" | "done" | "archived" | "deleted";

// filter type : 
export type FilterType = "all" | "done";

// geef TST mee in interface, roep aan in interface en geef mee als waarde.
export interface StatusTransition {
    value: TaskStatusType; 
    label: string;
}

export interface ColumnConfiguration{
    status: TaskStatusType;
    label : string;
}

