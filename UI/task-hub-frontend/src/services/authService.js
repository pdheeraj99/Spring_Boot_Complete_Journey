// src/services/authService.js
import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

const login = (email, password) => {
	return axios
		.post(API_URL + "login", {
			email,
			password,
		})
		.then((response) => {
			// If the login is successful and we get an accessToken
			if (response.data.accessToken) {
				// Save the user's token data to localStorage
				localStorage.setItem("user", JSON.stringify(response.data));
			}
			return response.data;
		});
};

const logout = () => {
	localStorage.removeItem("user");
	// We'll also call the backend logout endpoint later
};

const getCurrentUser = () => {
	return JSON.parse(localStorage.getItem("user"));
};

const authService = {
	login,
	logout,
	getCurrentUser,
	// We'll add register, logout, etc. here later
};

export default authService;
