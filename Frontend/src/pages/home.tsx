import TaskListCreate from "../components/taskListCreate";
import UserLogin from "../components/userLogin";
import { useUser } from "../stores/userStore";

const HomePage = () => {
    const user = useUser();
    return (
        <div className="home-container">
            {user.id && !isNaN(user.id) ? (
                <p className="logged-in-status">
                    Ingelogd als: {user.firstName} {user.lastName}
                </p>
            ) : (
                <p className="logged-in-status">Niet ingelogd</p>
            )}
            <h1>Home</h1>
            <UserLogin />
            <hr />
            <TaskListCreate />
        </div>
    );
};

export default HomePage;