import axiosClient from "./baseapi";

const authUserAPI = {
    authenticate(param) {
        return axiosClient.post('/api/v1/auth/user/', param);
    }
};

export default authUserAPI;