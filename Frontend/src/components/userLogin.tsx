import { useState } from "react";
import UserCreate from "./userCreate";
import { logout, updateUser } from "../stores/userStore";
import { API_URL } from "../App";
import type { UserSummaryDTO } from "../types/models.d";

const UserLogin = () => {
    const [makingAccount, setMakingAccount] = useState<number>(0);
    const [state, setState] = useState({ email: "" });
    const [error, setError] = useState<string>("");

    const handleSubmit = async (event: React.FormEvent<HTMLElement>) => {
        event.preventDefault();
        setError("");

        // Fetch all users and find by email
        const response = await fetch(`${API_URL}/users`);
        if (!response.ok) {
            setError("Kon gebruikers niet ophalen");
            return;
        }

        const users: UserSummaryDTO[] = await response.json();
        const loginUser = users.find((u) => u.email === state.email);

        if (!loginUser) {
            setError("Gebruiker niet gevonden");
            return;
        }

        updateUser({
            id: loginUser.id,
            firstName: loginUser.firstName,
            lastName: loginUser.lastName,
            email: loginUser.email,
        });
    };

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setState({ ...state, [name]: value });
    };

    if (makingAccount > 0) {
        return <UserCreate setMakingAccount={setMakingAccount} />;
    }

    return (
        <>
            <h3>Login</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label style={{ color: "black" }} htmlFor="email">
                        Email:
                    </label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={state.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                {error && <p style={{ color: "red" }}>{error}</p>}
                <button className="login" type="submit">
                    Login
                </button>
                <button className="loguit" type="button" onClick={() => logout()}>
                    Logout
                </button>
            </form>
            <hr />
            <p>
                Nog geen account?{" "}
                <button onClick={() => setMakingAccount(1)}>Maak een account</button>
            </p>
        </>
    );
};

export default UserLogin;
