// src/App.jsx
import { Link, Outlet } from "react-router-dom";

function App() {
	return (
		<div>
			<header style={{ padding: "1rem", background: "#222" }}>
				<h1>Task Hub</h1>
				<nav>
					<Link
						to="/login"
						style={{ marginRight: "1rem" }}>
						Login
					</Link>
					<Link to="/dashboard">Dashboard</Link>
				</nav>
			</header>
			<main style={{ padding: "1rem" }}>
				{/* Child pages will be rendered here */}
				<Outlet />
			</main>
		</div>
	);
}

export default App;
