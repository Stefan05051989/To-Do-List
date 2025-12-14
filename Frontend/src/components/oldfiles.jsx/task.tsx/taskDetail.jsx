import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../App";
import { Fragment, useState } from "react";
import CommentCreate from "./commentCreate";
import { useUser } from "../stores/userStore";
import TopicUpdate from "./topicUpdate";

const TaskDetail = ({ taskId, setTaskId }) => {

    // const [placingComment, setPlacingComment] = useState(0);
    const [editTask, setEditTask] = useState("");
    const queryClient = useQueryClient();
    const user = useUser();

    const deleteTopic = useMutation({
        mutationFn: async () => {
            const response = await fetch(`${API_URL}/topics/${topicId}`, {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' }
            });
            if (!response.ok) throw new Error('failed to delete topic');
            return;
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['topics'] });
            console.log("succes");
            setTopicId(NaN);
        }
    });

    const {
        data: topic,
        isLoading,
        error,
    } = useQuery({
        queryKey: ["topic", topicId],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/topics/${topicId}`);
            if (!response.ok) {
                throw new Error("topic error");
            }
            return response.json();
        },
    });

    if (error) {
        return <div style={{ color: "red" }}>Error: {error.message}</div>;
    }
    if (isLoading || topic == undefined) {
        return <div>Loading...</div>;
    }
    if (placingComment > 0) {
        return <CommentCreate setPlacingComment={setPlacingComment} topicId={topicId} />;
    }
    if (editingTopic) {
        return <TopicUpdate setEditingTopic={setEditingTopic} topicId={topicId} />;
    }

    return (
        <>
            <h3>Topic detail {topic.id}</h3>
            <h4>naam: {topic.name}</h4><br />
            <h6>gebruiker: {topic.user.name}</h6><br />
            <h4>inhoud:</h4><br />
            <div className="div"> {topic.topicContents}</div>
            <br />
            <h4>comments:
                <ul>
                    {topic.comments.map((comment) => (
                        <Fragment key={comment.id}>
                            <li>user: {comment.user} <p>comment: {comment.name}</p></li>
                            <br />
                        </Fragment>
                    ))}
                </ul>
            </h4>
            <br />
            {user.id > 0 && <button onClick={() => setPlacingComment(1)}>plaats comment</button>}
            {topic.user.id === user.id && <button onClick={() => setEditingTopic(1)}>aanpassen</button>}
            {topic.user.id === user.id && <button onClick={() => deleteTopic.mutate()}>delete topic</button>}
            <button onClick={() => setTopicId(NaN)}>terug</button>
        </>
    );
};

export default TopicDetail;
