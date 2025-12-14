import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";
import { API_URL } from "../App";
import { useUser } from "../stores/userStore";

const CommentCreate = ({ setPlacingComment, topicId }) => {

    const [comment, setState] = useState({ name: '' });
    const queryClient = useQueryClient();
    const user = useUser();

    const createComment = useMutation({
        mutationFn: async (comment) => {
            const response = await fetch(`${API_URL}/comments/${topicId}/${user.id}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(comment)
            });
            if (!response.ok) throw new Error('failed to create comment');
            return response.json();
        },
        onSuccess: () => {
            setState({ name: '' });
            queryClient.invalidateQueries({ queryKey: ['topic', topicId] });
            console.log("succes");
        }
    });

    const handleSubmit = (event) => {
        event.preventDefault();
        createComment.mutate(comment);
        console.log("Submitted " + comment.name);
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setState({ ...comment, [name]: value });
    };

    return (
        <>
            <h3>plaats een comment</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">Inhoud:</label>
                    <input type="text" id="name" name="name" value={comment.name} onChange={handleChange} />
                </div>
                <button type="submit" disabled={createComment.isPending}>plaats comment</button>
                <button onClick={() => setPlacingComment(0)}> terug</button>
            </form>
        </>
    );
};

export default CommentCreate;
