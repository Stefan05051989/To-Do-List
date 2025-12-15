import { useDroppable } from "@dnd-kit/core";
import { SortableContext, verticalListSortingStrategy } from "@dnd-kit/sortable";
import type { ColumnProps} from "../types/models.d";
import TaskCard from "./TaskCard";


const Column = ({ status, title, tasks }: ColumnProps) => {
    const { setNodeRef, isOver } = useDroppable({
        id: status,
    });

    return (
        <div className="board-column">
            <h2>{title}</h2>
            <div
                ref={setNodeRef}
                className={`board-tasks ${isOver ? "column-over" : ""}`}
                style={{
                    minHeight: "100px",
                    backgroundColor: isOver ? "#e0f7fa" : "transparent",
                    borderRadius: "8px",
                    padding: "8px",
                }}
            >
                <SortableContext items={tasks.map((t) => t.id)} strategy={verticalListSortingStrategy}>
                    {tasks.length > 0 ? (
                        tasks.map((task) => <TaskCard key={task.id} task={task} />)
                    ) : (
                        <p className="no-tasks">No Tasks</p>
                    )}
                </SortableContext>
            </div>
        </div>
    );
};

export default Column;