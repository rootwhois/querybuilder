import axios from "axios";

axios.defaults.baseURL = "http://127.0.0.1:8081/";

// 前置拦截
axios.interceptors.request.use((config) => {
    return config;
});