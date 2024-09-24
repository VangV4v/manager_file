import axiosClient from "./baseapi";

const fileAPI = {
    getAllFileByFolderAndUser(param, jwt) {
        return axiosClient.get(`/api/v1/files/folder/${param}/`, {
            headers: {
                Authorization: "Bearer " + jwt,
            }
        });
    },
    uploadFileByUser(param, jwt) {
        return axiosClient.post("/api/v1/files/", param, {
            headers: {
                Authorization: "Bearer " + jwt,
            }
        });
    }
};

export default fileAPI;