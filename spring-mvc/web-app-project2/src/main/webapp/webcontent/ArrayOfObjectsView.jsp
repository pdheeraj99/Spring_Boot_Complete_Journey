<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- This below uri is required for JSTL to work correctly --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Objects</title>
</head>
<body>

<center>
        <h1>Display Course Objects</h1>
    
        <c:forEach var="names" items="${books}">
            
            ${names}
        
        </c:forEach>
</center>

</body>
</html>