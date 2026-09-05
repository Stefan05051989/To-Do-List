import { createStore } from "@odemian/react-store"; // global state useUser etc zonder propdrilling.
import type { User } from "../types/models.d";

const STORAGE_KEY = "todoapp_user";
const defaultUser: User = {
    id: NaN,
    firstName: "",
    lastName: "",
    email: "",
};

function loadUserFromStorage(): User {
    try {
        const stored = localStorage.getItem(STORAGE_KEY);
        if (!stored) return defaultUser;
        const parsed = JSON.parse(stored);
        return {
            id: parsed.id ?? NaN,
            firstName: parsed.firstName ?? "",
            lastName: parsed.lastName ?? "",
            email: parsed.email ?? "",
        };
    } catch {
        return defaultUser;
    }
}

export const [useUser, updateUserStore] = createStore<User>(loadUserFromStorage());
export const updateUser = (user: User) => {
    updateUserStore(user);
    localStorage.setItem(STORAGE_KEY, JSON.stringify(user));
};
export const logout = () => {
    const empty = { id: NaN, firstName: "", lastName: "", email: "" };
    updateUserStore(empty);
    localStorage.removeItem(STORAGE_KEY);
};