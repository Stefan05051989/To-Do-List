import { useQuery } from "@tanstack/react-query";
import { useUser } from "../stores/userStore";
import { API_URL } from "../App";
import type { ToDoUserSummaryDTO } from "../types/models.d";

const UserDetail = () => {
    const user = useUser();

    const {
        data: userDetail,
        isLoading,
        error,
    } = useQuery<ToDoUserSummaryDTO>({
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
        return <div>Sign in to see your profile.</div>;
    }

    if (error) {
        return <div >Error : {error.message}</div>;
    }

    if (isLoading || userDetail == undefined) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <div>
                <h3>First name :  <p>{userDetail.firstName}</p></h3>
            </div>
            <div>
                <h3>Last name : <p>{userDetail.lastName}</p></h3>
            </div>
            <div>
                <h3>E-mail : <p>{userDetail.email}</p></h3>
            </div>
        </>
    );
};

export default UserDetail;
