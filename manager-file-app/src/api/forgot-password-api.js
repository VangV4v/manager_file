import axiosClient from "./baseapi";

const forgotPasswordAPI = {

    updatePassword(param) {

        return axiosClient.post("/api/v1/forgot-password/update/", param);
    }
};

export default forgotPasswordAPI;