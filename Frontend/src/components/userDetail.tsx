import { useQuery } from "@tanstack/react-query";
import { useUser } from "../stores/userStore";
import { API_URL } from "../App";
import type { UserSummaryDTO } from "../types/models.d";  // ✅ Fix type naam

const UserDetail = () => {
    const user = useUser();

    const {
        data: userDetail,
        isLoading,
        error,
    } = useQuery<UserSummaryDTO>({
        queryKey: ["user", user.id],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users/${user.id}`);
            if (!response.ok) {
                throw new Error("user error");
            }
            return response.json();
        },
        enabled: !isNaN(user.id) && user.id > 0,
    });

    if (!user.id || isNaN(user.id)) {
        return <div>Log in om je profiel te zien.</div>;
    }

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    if (!userDetail) {
        return <div>Geen gebruiker gevonden.</div>;
    }

    return (
        <div className="user-detail">
            <div>
                <strong>Voornaam:</strong> {userDetail.firstName}
            </div>
            <div>
                <strong>Achternaam:</strong> {userDetail.lastName}
            </div>
            <div>
                <strong>E-mail:</strong> {userDetail.email}
            </div>
        </div>
    );
};

export default UserDetail;