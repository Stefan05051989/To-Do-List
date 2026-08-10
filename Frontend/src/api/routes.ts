import { API_URL } from "../api/config";

// local file for all backend API-paths

export const API_ROUTES = { 
    // Tasklist
    tasklist: `${API_URL}/tasklist`,
    tasklistById: (id: number) => `${API_URL}/tasklist/${id}`,
    tasklistsByUserId: (userId: number) => `${API_URL}/tasklist/user/${userId}`,

    // Tasks 
    task:`${API_URL}/task`,
    taskById:(id: number) =>`${API_URL}/task/${id}`,


    // Users
    users: `${API_URL}/task`,
    userById:(id: number) => `${API_URL}/users/${id}`

}