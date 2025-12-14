import { createStore } from "@odemian/react-store";

interface User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
}

export const [useUser, updateUser] = createStore<User>({
    id: NaN,
    firstName: "",
    lastName: "",
    email: "",
});

export const logout = () => {
    updateUser({ id: NaN,  firstName: "", lastName: "", email: "" });
};
