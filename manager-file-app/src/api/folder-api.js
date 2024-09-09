import axiosClient from "./baseapi";

const folderAPI = {

    getFoldersByUser(jwt) {

        return axiosClient.get('/api/v1/folders/user/', {
            headers: {
                Authorization: "Bearer " + jwt,
            }
        })
    },
    createFolderByUser(jwt, data) {
        return axiosClient.post('/api/v1/folders/', data, {
            headers: {
                Authorization: "Bearer " + jwt,
            }
        });
    }
};

export default folderAPI;