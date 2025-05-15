<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }
        form {
            width: 300px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        .required {
            color: red;
        }
        input[type="number"], input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="number"]:focus, input[type="text"]:focus {
            border-color: #4CAF50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
            outline: none;
        }
        input::placeholder {
            color: #999;
            font-style: italic;
        }
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Register</h1>
    <form action="/webApp3/register" method="post">
        <div class="form-group">
            <label for="id">ID: <span class="required">*</span></label>
            <input type="number" id="id" name="id" placeholder="Enter ID" required>
        </div>
        <div class="form-group">
            <label for="ename">Employee Name: <span class="required">*</span></label>
            <input type="text" id="ename" name="ename" placeholder="Enter Employee Name" required>
        </div>
        <div class="form-group">
            <label for="ecity">City: <span class="required">*</span></label>
            <input type="text" id="ecity" name="ecity" placeholder="Enter City" required>
        </div>
        <div class="form-group">
            <label for="esalary">Salary: <span class="required">*</span></label>
            <input type="number" step="0.01" id="esalary" name="esalary" placeholder="Enter Salary" required>
        </div>
        <input type="submit" value="Register">
    </form>
</body>
</html>