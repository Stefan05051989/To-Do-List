import { useMutation, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../App";
import { useState } from "react";

const UserCreate = ({ setMakingAccount }) => {

    const queryClient = useQueryClient();
    const [state, setState] = useState({ name: '' });

    const createUser = useMutation({
        mutationFn: async (user) => {
            const response = await fetch(`${API_URL}/users`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(user)
            });
            if (!response.ok) throw new Error('failed to create topic');
            return response.json();
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['users'] });
            setState({ name: '' });
        }
    });

    const handleSubmit = (event) => {
        event.preventDefault();
        createUser.mutate(state);
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setState({ ...state, [name]: value });
    };

    return (
        <>
            <h3>Create Account : </h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">gebruikers naam:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={state.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" disabled={createUser.isPending}>maak account</button>
                <div></div>
                <span>al een account?</span>
                <button onClick={() => setMakingAccount(0)}>terug naar login</button>
            </form>
        </>
    );
};
