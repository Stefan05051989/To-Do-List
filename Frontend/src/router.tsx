import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./pages/home";
import TaskListPage from "./pages/taskList";
import MainLayout from "./layout";
import UserPage from "./pages/user";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<MainLayout />}>
          <Route index element={<HomePage />} />
          <Route path="taskList" element={<TaskListPage />} /> 
          <Route path="user" element={<UserPage />} /> 
        </Route>
      </Routes>
    </BrowserRouter>
  )
};

export default Router;