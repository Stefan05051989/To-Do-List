import { useQuery } from "@tanstack/react-query";
import { useUser } from "../stores/userStore";
import { API_URL } from "../App";

const UserDetail = () => {
    const user = useUser();

    const {
        data: userDetail,
        isLoading,
        error,
    } = useQuery({
        queryKey: ["users"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users/${user.id}`);
            if (!response.ok) {
                throw new Error("user error");
            }
            return response.json();
        },
    });

    if (error) {
        return <div style={{ color: "red" }}>Error: {error.message}</div>;
    }
    if (isLoading || userDetail == undefined) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <div><h3>naam: <p>{userDetail.name}</p></h3></div>
            <div>
                <h4>topics: </h4>
                <br></br>
                {userDetail.topics?.map((topic) => (
                    <>
                        <li key={topic.id}>
                            {topic.name}
                        </li>
                        <br></br>
                    </>
                ))}
            </div>
            <div>
                <h4>comments: </h4>
                {userDetail.comments?.map((comment) => (
                    <li key={comment.id}>
                        {comment.name}
                    </li>
                ))}
            </div>
        </>
    );
};

export default UserDetail;
