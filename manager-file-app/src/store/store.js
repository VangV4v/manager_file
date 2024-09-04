import { configureStore } from "@reduxjs/toolkit";
import authUserReducer from "../slice/auth-user-slice";

const defaultReducer = {
    authUserReducer: authUserReducer
};

const store = configureStore({
    reducer: defaultReducer
});

export default store;