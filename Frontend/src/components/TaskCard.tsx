import { useSortable } from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";
import { type TaskCardProps } from "../types/models.d";

const TaskCard = ({ task }: TaskCardProps) => {
    const { attributes, listeners, setNodeRef, transform, transition, isDragging } = useSortable({
        id: task.id,
        data: { task },
    });

    const style = {
        transform: CSS.Transform.toString(transform),
        transition,
        opacity: isDragging ? 0.5 : 1,
        cursor: "grab",
    };

    return (
        <div
            ref={setNodeRef}
            style={style}
            {...attributes}
            {...listeners}
            className="task-card"
        >
            <p>{task.title}</p>
            {task.content && <small>{task.content}</small>}
        </div>
    );
};

export default TaskCard;