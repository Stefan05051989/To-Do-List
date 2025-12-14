import { NavLink, Outlet } from "react-router-dom";
import { useUser } from "./stores/userStore";

const MainLayout = () => {
  const user = useUser();

  return (
    <>
      <nav className="navbar">
        <NavLink
          to="/"
          end
          className={({ isActive }) =>
            isActive ? "nav-link active" : "nav-link"
          }
        >
          Home
        </NavLink>
        {user.id && !isNaN(user.id) ? (
          <>
            <NavLink
              to="/taskList"
              end
              className={({ isActive }) =>
                isActive ? "nav-link active" : "nav-link"
              }
            >
              Task List{" "}
            </NavLink>
            <NavLink
              to="/user"
              end
              className={({ isActive }) =>
                isActive ? "nav-link active" : "nav-link"
              }
            >
              User{" "}
            </NavLink>
          </>
        ) : (
          <></>
        )}
      </nav>
      <main className="main-content">
        <Outlet />
      </main>
    </>
  );
};

export default MainLayout;
