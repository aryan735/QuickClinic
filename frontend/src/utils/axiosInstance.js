import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: "http://localhost:8085", // Add Gateway base URL here
});

// Automatically attach token if available
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;
