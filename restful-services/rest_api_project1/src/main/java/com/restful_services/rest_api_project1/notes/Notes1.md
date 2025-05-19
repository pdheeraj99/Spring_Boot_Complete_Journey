**QUESTION 1**

**HTTP Methods and Request Types**
=====================================

### Query Parameters and Path Parameters

* **Query parameters** (e.g., `http://example.com/users?name=John&age=30`): Usually a **GET** request, as you're retrieving data from the server based on the query parameters.
* **Path parameters** (e.g., `http://example.com/users/123`): Also usually a **GET** request, as you're retrieving data from the server based on the path parameters.

### Request Body

* **Request body** (e.g., JSON or XML data in the request body): Not necessarily a **POST** request. While **POST** is a common method used for sending data in the request body, other HTTP methods like **PUT**, **PATCH**, and **DELETE** can also use request bodies.

### HTTP Method Guidelines

* **GET**: Retrieve data from the server (query parameters, path parameters)
* **POST**: Create new data on the server (request body)
* **PUT**: Update existing data on the server (request body)
* **PATCH**: Partially update existing data on the server (request body)
* **DELETE**: Delete data from the server (path parameters or request body)

Note: These are general guidelines, and the specific use of HTTP methods can vary depending on the API design and requirements.