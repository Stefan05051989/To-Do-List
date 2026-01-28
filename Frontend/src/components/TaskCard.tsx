import { useSortable } from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";
import { type TaskCardProps } from "../types/models.d";

type Task = TaskCardProps extends { task: infer T } ? T : unknown;
type Props = { task: Task; onClick?: (task: Task) => void };

const TaskCard = ({ task, onClick }: Props) => {
    const { attributes, listeners, setNodeRef, transform, transition, isDragging } = useSortable({
        id: task.id,
        data: { task },
    });

    const style: React.CSSProperties = {
        transform: CSS.Transform.toString(transform),
        transition,
        opacity: isDragging ? 0.5 : 1,
        cursor: "pointer",
    };

    const handleClick = () => onClick?.(task);

    return (
    <div
      ref={setNodeRef}
      style={style}
      {...attributes}
      {...listeners}
      role="button"
      tabIndex={0}
      onClick={handleClick}
      onKeyDown={(e) => {
        if (e.key === "Enter") handleClick();
      }}
      className="task-card"
    >
      <div className="task-title">{task.title}</div>

      {task.content ? (
        <div className="task-preview">{task.content.slice(0, 80)}...</div>
      ) : null}
    </div>
  );
};

export default TaskCard;