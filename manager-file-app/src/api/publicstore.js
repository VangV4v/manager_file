import axiosClient from "./baseapi";

const publicStoreAPI = {

    uploadFile(param) {

        return axiosClient.post('/api/v1/public-store/', param);
    }
};

export default publicStoreAPI;