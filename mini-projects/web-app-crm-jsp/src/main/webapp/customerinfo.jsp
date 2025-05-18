<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRM Application</title>
<link type=text/css rel="stylesheet" href="./style.css">
</head>
<body>
    <div id="wrapper">
    	<div id="header">
    			<h1>Customer Relationship Management Application</h1>
    	</div>
    </div>
    
    <div id="container">
    
    	<div id="content">
    
   <!--  	<!-- <a href="/CRM/showForm">Click here to register</a> -->
    	<input type="button" value="Register Customer" 
    	onClick="window.location.href='/crmapp/show-form'; return false;" 
    	class="add-button" /> 
    	    <table border="1">
    	    
    	           <tr>
    	           <th>First Name</th>
    	           <th>Last Name</th>
    	           <th>City</th>
    	           <th>Update Data</th>
    	           <th>Delete Data</th>
    	       
    	           </tr>
    	           <c:forEach var="customer"  items="${customers}">
    	           
    	           <c:url var="UpdateLink" value="/update-form"> 
    	           
    	           <c:param name="cxid" value="${customer.id}"></c:param>
    	           
    	           </c:url>
    	           <c:url var="DeleteLink" value="/delete-data"> 
    	           <c:param name="cxid" value="${customer.id}"></c:param>
    	           
    	           </c:url>
    	          
    	           
    	           <tr>
    	              <td>${customer.firstName}</td>
    	              <td>${customer.lastName}</td>
    	              <td>${customer.city}</td>
    	              
    	           <td><a href="${UpdateLink}">Update Information</a> </td>
    	           <td><a href="${DeleteLink}">Delete Information</a> </td>
    	              
    	           </tr>
    	           
    	           </c:forEach>
    	    
    	    </table>
    	
    	
    	</div>
    
    </div>
</body>
</html>