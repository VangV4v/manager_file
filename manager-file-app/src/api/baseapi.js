import axios from "axios";

const axiosClient = axios.create({
    baseURL: 'http://192.168.160.1:2002',
});

export default axiosClient;