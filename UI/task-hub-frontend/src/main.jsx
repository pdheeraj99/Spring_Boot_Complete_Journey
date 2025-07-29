// src/main.jsx
import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import App from "./App.jsx";
import "./index.css";
import DashboardPage from "./pages/DashboardPage.jsx";
import LoginPage from "./pages/LoginPage.jsx";

import CssBaseline from "@mui/material/CssBaseline"; // Import this
import { createTheme, ThemeProvider } from "@mui/material/styles"; // Import these

const darkTheme = createTheme({
	palette: {
		mode: "dark",
	},
});

const router = createBrowserRouter([
	{
		path: "/",
		element: <App />,
		children: [
			{
				index: true, // This makes it the default child route for '/'
				element: <h2>Welcome to the Task Hub Home Page!</h2>,
			},
			{
				path: "dashboard",
				element: <DashboardPage />,
			},
		],
	},
	{
		path: "/login",
		element: <LoginPage />,
	},
]);

ReactDOM.createRoot(document.getElementById("root")).render(
	<React.StrictMode>
		<ThemeProvider theme={darkTheme}>
			<CssBaseline /> {/* This ensures a consistent baseline style */}
			<RouterProvider router={router} />
		</ThemeProvider>
	</React.StrictMode>,
);
