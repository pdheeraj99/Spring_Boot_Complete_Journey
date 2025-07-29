// src/pages/LoginPage.jsx
import { Box, Button, Container, TextField, Typography } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import authService from "../services/authService";

const LoginPage = () => {
	const navigate = useNavigate();
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [loading, setLoading] = useState(false);
	const [message, setMessage] = useState("");

	const handleLogin = (event) => {
		event.preventDefault();
		setMessage("");
		setLoading(true);

		authService.login(email, password).then(
			() => {
				navigate("/dashboard");
				window.location.reload(); // Simple way to refresh and update app state
			},
			(error) => {
				const resMessage =
					(error.response &&
						error.response.data &&
						error.response.data.message) ||
					error.message ||
					error.toString();

				setLoading(false);
				setMessage(resMessage);
				alert("Login Failed!");
			},
		);
	};

	return (
		<Container
			component="main"
			maxWidth="xs">
			<Box
				sx={{
					marginTop: 8,
					display: "flex",
					flexDirection: "column",
					alignItems: "center",
				}}>
				<Typography
					component="h1"
					variant="h5">
					Sign in
				</Typography>
				<Box
					component="form"
					onSubmit={handleLogin}
					noValidate
					sx={{ mt: 1 }}>
					<TextField
						margin="normal"
						required
						fullWidth
						id="email"
						label="Email Address"
						name="email"
						value={email}
						onChange={(e) => setEmail(e.target.value)}
						autoFocus
					/>
					<TextField
						margin="normal"
						required
						fullWidth
						name="password"
						label="Password"
						type="password"
						id="password"
						value={password}
						onChange={(e) => setPassword(e.target.value)}
					/>
					<Button
						type="submit"
						fullWidth
						variant="contained"
						sx={{ mt: 3, mb: 2 }}
						disabled={loading}>
						{loading ? "Signing in..." : "Sign In"}
					</Button>
					{message && <Typography color="error">{message}</Typography>}
				</Box>
			</Box>
		</Container>
	);
};

export default LoginPage;
