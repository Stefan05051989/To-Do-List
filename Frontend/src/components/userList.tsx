import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../App";
import type { ToDoUserSummaryDTO } from "../types/models.d";

const UserList = () => {
    const {
        data: users,
        isLoading,
        error,
    } = useQuery({
        queryKey: ["users"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users`);
            if (!response.ok) {
                throw new Error("user error");
            }
            return response.json();
        },
    });

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div style={{ color: "red" }}>Error: {error.message}</div>;
    }

    return (
        <div>
            <h2>User lijst</h2>
            {users && users.length > 0 ? (
                <>
                    {users.map((user: ToDoUserSummaryDTO) => (
                        <div key={user.id}>
                            <br />
                            <li>
                                <p>
                                    id: {user.id} | {user.firstName} {user.lastName} | {user.email}
                                </p>
                            </li>
                        </div>
                    ))}
                </>
            ) : (
                <p>Geen users gevonden</p>
            )}
        </div>
    );
};

export default UserList;
