import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../App";

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
            <h2>User List</h2>
            {users && users.length > 0 ? (
                <>
                    {users.map((user) => (
                        <>
                            <br></br>
                            <li key={user.id}>
                                <p>Id: {user.id} Name: {user.name}</p>
                            </li>
                        </>
                    ))}
                </>
            ) : (
                <p>No Users found.</p>
            )}
        </div>
    );
};

export default UserList;
