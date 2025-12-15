import { useMutation, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../App";
import type { UserCreateDTO } from "../types/models.d";
import { useState } from "react";

interface UserCreateProps {
    setMakingAccount: (id: number) => void;
}

const UserCreate = ({ setMakingAccount }: UserCreateProps) => {
    const queryClient = useQueryClient();
    const [state, setState] = useState<UserCreateDTO>({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });

    const createUser = useMutation({
        mutationFn: async (user: UserCreateDTO) => {
            const response = await fetch(`${API_URL}/users`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(user),
            });
            if (!response.ok) throw new Error("failed to create user");
            return response.json();
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["users"] });
            setState({ firstName: "", lastName: "", email: "", password: "" });
            setMakingAccount(0);
        },
    });

    const handleSubmit = (event: React.FormEvent<HTMLElement>) => {
        event.preventDefault();
        createUser.mutate(state);
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setState({ ...state, [name]: value });
    };

    return (
        <>
            <h3>Maak een account</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="firstName">Voornaam:</label>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={state.firstName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="lastName">Achternaam:</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={state.lastName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={state.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">Wachtwoord:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={state.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" disabled={createUser.isPending}>
                    Maak account
                </button>
                <div></div>
                <span>Al een account?</span>
                <button type="button" onClick={() => setMakingAccount(0)}>
                    Terug naar login
                </button>
            </form>
        </>
    );
};

export default UserCreate;
