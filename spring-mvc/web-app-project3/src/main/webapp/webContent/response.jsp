<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Response</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        .card {
            width: 500px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        p {
            text-align: center;
            color: #4CAF50;
            font-size: 18px;
            margin-bottom: 20px;
        }
        .details-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .details-table th, .details-table td {
            padding: 10px;
            text-align: left;
        }
        .details-table th {
            font-weight: bold;
            color: #555;
            width: 40%;
        }
        .details-table td {
            color: #333;
        }
        .details-table tr {
            border-bottom: 1px solid #ddd;
        }
        .details-table tr:last-child {
            border-bottom: none;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            text-align: center;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="card">
        <center>
        <h2>Registration Successful ( ONE - WAY )</h2>
        </center>
        <p>Employee details have been registered successfully.</p>
        <table class="details-table">
            <tr>
                <th>ID:</th>
                <td>${emp.id}</td>
            </tr>
            <tr>
                <th>Employee Number:</th>
                <td>${emp.ename}</td>
            </tr>
            <tr>
                <th>City:</th>
                <td>${emp.ecity}</td>
            </tr>
            <tr>
                <th>Salary:</th>
                <td>${emp.esalary}</td>
            </tr>
        </table>
        <div style="text-align: center;">
            <a href="/register" class="button">Register Another Employee</a>
        </div>
    </div>
</body>
</html>