import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authUserAPI from "../api/auth-user";

export const authenticateUser = createAsyncThunk("auth/authUser",
    async (param) => {

        const response = await authUserAPI.authenticate(param);
        const authUser = {
            authenticated: response.data.success,
            jwt: response.data.jwt,
            expiration: response.data.expiration,
            role: response.data.role,
        }
        return authUser;
    }
)

const AuthUserSlice = createSlice({
    name: "userAuthenticate",
    initialState: {
        authUser: !localStorage.getItem("authUserInformation") ? {
            authenticated: false,
            jwt: "",
            expiration: 0,
            role: ""
        } : {
            authenticated: JSON.parse(localStorage.getItem("authUserInformation")).authenticated,
            jwt: JSON.parse(localStorage.getItem("authUserInformation")).jwt,
            expiration: JSON.parse(localStorage.getItem("authUserInformation")).expiration,
            role: JSON.parse(localStorage.getItem("authUserInformation")).role
        }
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(authenticateUser.fulfilled, (state, action) => {
            state.authUser = action.payload;
        })
    }
});

const { reducer } = AuthUserSlice;
export default reducer;