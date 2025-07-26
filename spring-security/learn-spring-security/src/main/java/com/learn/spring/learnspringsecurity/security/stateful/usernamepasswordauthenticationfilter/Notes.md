## Summary: Form-Based Login with `UsernamePasswordAuthenticationFilter`

### Goal

To authenticate a user based on a username and password submitted via an HTML form (typically a `POST` request to `/login`). This is the standard mechanism for **stateful**, session-based web applications.

### Key Components (The Team)

* **`UsernamePasswordAuthenticationFilter`**: The entry point. It's a specialist filter that only watches for `POST` requests to a specific login URL (e.g., `/login`).
* **`AuthenticationManager`**: The manager. The filter delegates the actual verification task to this component.
* **`DaoAuthenticationProvider`**: The specialist that handles database authentication. It coordinates the `UserDetailsService` and `PasswordEncoder`.
* **`UserDetailsService`**: The data fetcher. This is the interface you implement (`OurCustomUserDetailsService`) to tell Spring Security how to find a user in your database.
* **`UserDetails`**: The data container. The object returned by `UserDetailsService`, which holds the user's username, hashed password, and roles/authorities.
* **`PasswordEncoder`**: The verifier. Securely compares the plain-text password submitted by the user with the hashed password stored in the database.

### The Authentication Flow (Step-by-Step)

1.  **User Submits Form**: The user sends a `POST` request to `/login` with `username` and `password` in the request body (`x-www-form-urlencoded`).
2.  **Filter Intercepts**: The `UsernamePasswordAuthenticationFilter` intercepts this specific request.
3.  **Token Creation**: The filter creates an `UsernamePasswordAuthenticationToken` (which is in an "unauthenticated" state) containing the credentials submitted by the user.
4.  **Delegation**: The filter passes this token to the `AuthenticationManager` to be authenticated.
5.  **Provider Selection**: The `AuthenticationManager` selects the appropriate `AuthenticationProvider`. For database authentication, this is the `DaoAuthenticationProvider`.
6.  **User Lookup**: The `DaoAuthenticationProvider` calls your custom `UserDetailsService`'s `loadUserByUsername()` method to fetch the corresponding `UserDetails` object from the database.
7.  **Password Verification**: The provider then uses the `PasswordEncoder` to compare the `rawPassword` from the token with the `encodedPassword` from the `UserDetails` object.
8.  **Result Handling**:
    * **SUCCESS**: If the passwords match, the `DaoAuthenticationProvider` returns a new, fully **authenticated** `Authentication` object, now populated with the user's authorities (roles).
    * **FAILURE**: If the user is not found or the passwords do not match, an `AuthenticationException` is thrown.
9.  **Context Update (on Success)**: The `UsernamePasswordAuthenticationFilter` receives the fully authenticated object and updates the `SecurityContextHolder` with it. This action officially marks the user as "logged in" for the current session.
10. **Final Action**: The filter then invokes either the `AuthenticationSuccessHandler` (which by default redirects to the home page) or the `AuthenticationFailureHandler` (which by default redirects back to `/login?error`).

### Postman Test Requests 🧪

#### 1\. User Registration Request

* **Method**: `POST`
* **URL**: `http://localhost:8080/api/auth/register`
* **Body**: `raw` -\> `JSON`
* **Content**:
  ```json
  {
      "firstName": "Dheeraj",
      "lastName": "Pilla",
      "email": "dheeraj.pilla@example.com",
      "password": "password123"
  }
  ```

#### 2\. User Login Request

* **Method**: `POST`
* **URL**: `http://localhost:8080/login`
* **Body**: `x-www-form-urlencoded`
* **Content**:
```json
  | KEY      | VALUE                       |
  | :------- | :-------------------------- |
  | `username` | `dheeraj.pilla@example.com` |
  | `password` | `password123`               |
  