<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

    
<html>
    
    <head>
        <title>Spring Boot - Student Management</title>
    </head>


    <body>
        Spring Boot - Welcome to Student Management
    </body>
    
    
    <c:url var="listUrl" value= "/students/list"/>

    <hr>
    
    <a href="${listUrl}">Back to Students List</a>    
    
</html>

    