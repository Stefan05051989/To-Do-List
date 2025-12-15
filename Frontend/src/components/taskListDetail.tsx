import { useMemo, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../App";
import type { Status, TaskListDetailProps, TaskListSummaryDTO, TaskSummaryDTO, TaskUpdateDTO } from "../types/models.d";
import TaskCreate from "./taskCreate";
import { useUser } from "../stores/userStore";
import { DndContext, type DragEndEvent } from "@dnd-kit/core";
import { useDroppable, useDraggable } from "@dnd-kit/core";
import { CSS } from "@dnd-kit/utilities";

const BOARD_STATUSES: Status[] = ["CREATED", "IN_PROGRESS", "DONE", "ARCHIVED"];

function prettyLabel(status: Status) {
    switch (status) {
        case "CREATED":
            return "Created";
        case "IN_PROGRESS":
            return "In Progress";
        case "DONE":
            return "Done";
        case "ARCHIVED":
            return "Archived";
        default:
            return status;
    }
}

function TaskColumnDroppable({
    status,
    children,
}: {
    status: Status;
    children: React.ReactNode;
}) {
    const { setNodeRef, isOver } = useDroppable({ id: status });

    return (
        <div
            ref={setNodeRef}
            style={{
                border: "2px dashed #cfcfcf",
                borderRadius: 8,
                padding: 12,
                minHeight: 280,
                background: isOver ? "rgba(0,0,0,0.03)" : "transparent",
            }}
        >
            <h3 style={{ textAlign: "center", marginTop: 0 }}>{prettyLabel(status)}</h3>
            {children}
        </div>
    );
}

function TaskCardDraggable({ task }: { task: TaskSummaryDTO }) {
    const { attributes, listeners, setNodeRef, transform, isDragging } = useDraggable({
        id: String(task.id),
    });

    const style: React.CSSProperties = {
        transform: CSS.Translate.toString(transform),
        opacity: isDragging ? 0.6 : 1,
        cursor: "grab",
        userSelect: "none",
        border: "1px solid #e7e7e7",
        borderRadius: 6,
        padding: "10px 12px",
        marginBottom: 10,
        background: "white",
    };

    return (
        <div ref={setNodeRef} style={style} {...listeners} {...attributes}>
            <div style={{ fontWeight: 600 }}>{task.title}</div>
            {task.content ? (
                <div style={{ fontSize: 12, opacity: 0.75, marginTop: 4 }}>{task.content}</div>
            ) : null}
        </div>
    );
}

const TaskListDetail = ({ taskListId, setTaskListId }: TaskListDetailProps) => {
    const [placingTask, setPlacingTask] = useState<number>(0);
    const user = useUser();
    const queryClient = useQueryClient();

    // Fetch TaskList details
    const {
        data: taskList,
        isLoading: taskListLoading,
        error: taskListError,
    } = useQuery<TaskListSummaryDTO>({
        queryKey: ["taskLists", taskListId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/tasklists/${taskListId}`);
            if (!response.ok) throw new Error("tasklist error");
            return response.json();
        },
    });

    // Fetch Tasks for this TaskList
    const {
        data: tasks = [],  // ✅ Default empty array
        isLoading: tasksLoading,
        error: tasksError,
    } = useQuery<TaskSummaryDTO[]>({
        queryKey: ["tasks", taskListId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/task/tasklist/${taskListId}`);
            if (!response.ok) throw new Error("task error");
            return response.json();
        },
    });

    const updateTaskMutation = useMutation({
    mutationFn: async (payload: { id: number; dto: TaskUpdateDTO }) => {
        const res = await fetch(`${API_URL}/task/${payload.id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload.dto),
        });
        if (!res.ok) throw new Error("update task failed");
        return res.json();
    },
    onMutate: async (payload) => {
        await queryClient.cancelQueries({ queryKey: ["tasks", taskListId] });

        const previousTasks = queryClient.getQueryData<TaskSummaryDTO[]>(["tasks", taskListId]);

        queryClient.setQueryData<TaskSummaryDTO[]>(["tasks", taskListId], (old) =>
            old?.map((t) =>
                t.id === payload.id ? { ...t, status: payload.dto.status } : t
            ) || []
        );

        return { previousTasks };
    },
    onError: (_err, _payload, context) => {
        if (context?.previousTasks) {
            queryClient.setQueryData(["tasks", taskListId], context.previousTasks);
        }
    },
    onSuccess: (data, payload) => {
        queryClient.setQueryData<TaskSummaryDTO[]>(["tasks", taskListId], (old) =>
            old?.map((t) => (t.id === payload.id ? data : t)) || []
        );
    },
});

    // ✅ FIXED: Gebruik "tasks" in plaats van "localTasks"
    const tasksByStatus = useMemo(() => {
        const map: Record<string, TaskSummaryDTO[]> = {};
        for (const s of BOARD_STATUSES) map[s] = [];
        for (const t of tasks) {  // ✅ tasks, niet localTasks
            if (map[t.status]) map[t.status].push(t);
        }
        return map as Record<Status, TaskSummaryDTO[]>;
    }, [tasks]);  // ✅ tasks, niet localTasks

    // ✅ FIXED: Geen setLocalTasks meer
    const onDragEnd = (event: DragEndEvent) => {
        const { active, over } = event;
        if (!over) return;

        const taskId = Number(active.id);
        const newStatus = over.id as Status;

        if (!BOARD_STATUSES.includes(newStatus)) return;

        const current = tasks.find((t) => t.id === taskId);  // ✅ tasks
        if (!current) return;
        if (current.status === newStatus) return;

        // Update backend
        updateTaskMutation.mutate({
            id: taskId,
            dto: {
                title: current.title,
                content: current.content,
                status: newStatus,
            },
        });
    };

    if (taskListError || tasksError) {
        return (
            <div style={{ color: "red" }}>
                Error: {taskListError?.message || tasksError?.message}
            </div>
        );
    }

    if (taskListLoading || tasksLoading || !taskList) {
        return <div>Loading...</div>;
    }

    if (placingTask > 0) {
        return <TaskCreate setPlacingTask={setPlacingTask} taskListId={taskListId} />;
    }

    return (
        <>
            <div style={{ display: "flex", alignItems: "center", gap: 12 }}>
                <button onClick={() => setTaskListId(NaN)}>← Terug</button>
                <h1 style={{ margin: 0 }}>{taskList.title}</h1>
            </div>

            <div style={{ marginTop: 12, marginBottom: 12 }}>
                {user.id > 0 && <button onClick={() => setPlacingTask(1)}>Nieuwe Task</button>}
                {updateTaskMutation.isPending ? (
                    <span style={{ marginLeft: 10, fontSize: 12, opacity: 0.7 }}>Saving...</span>
                ) : null}
            </div>

            <DndContext onDragEnd={onDragEnd}>
                <div
                    style={{
                        display: "grid",
                        gridTemplateColumns: "repeat(3, 1fr)",
                        gap: 16,
                    }}
                >
                    <TaskColumnDroppable status="CREATED">
                        {tasksByStatus.CREATED.length ? (
                            tasksByStatus.CREATED.map((t) => <TaskCardDraggable key={t.id} task={t} />)
                        ) : (
                            <div style={{ textAlign: "center", opacity: 0.6, fontStyle: "italic" }}>
                                No Tasks
                            </div>
                        )}
                    </TaskColumnDroppable>

                    <TaskColumnDroppable status="IN_PROGRESS">
                        {tasksByStatus.IN_PROGRESS.length ? (
                            tasksByStatus.IN_PROGRESS.map((t) => <TaskCardDraggable key={t.id} task={t} />)
                        ) : (
                            <div style={{ textAlign: "center", opacity: 0.6, fontStyle: "italic" }}>
                                No Tasks
                            </div>
                        )}
                    </TaskColumnDroppable>

                    <TaskColumnDroppable status="DONE">
                        {tasksByStatus.DONE.length ? (
                            tasksByStatus.DONE.map((t) => <TaskCardDraggable key={t.id} task={t} />)
                        ) : (
                            <div style={{ textAlign: "center", opacity: 0.6, fontStyle: "italic" }}>
                                No Tasks
                            </div>
                        )}
                    </TaskColumnDroppable>
                </div>

                <div style={{ marginTop: 16 }}>
                    <TaskColumnDroppable status="ARCHIVED">
                        {tasksByStatus.ARCHIVED.length ? (
                            tasksByStatus.ARCHIVED.map((t) => <TaskCardDraggable key={t.id} task={t} />)
                        ) : (
                            <div style={{ textAlign: "center", opacity: 0.6, fontStyle: "italic" }}>
                                No Tasks
                            </div>
                        )}
                    </TaskColumnDroppable>
                </div>
            </DndContext>
        </>
    );
};

export default TaskListDetail;