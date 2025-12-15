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
            <strong>Home</strong>
            <UserLogin />
            {/* <hr /> */}
        </div>
    );
};

export default HomePage;