<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Home</title>
</head>
<body>
    <h2>Welcome, User!</h2>
    <p>Hello, <c:out value="${sessionScope.loggedInUser.username}" /> (Role: User)</p>
    <a href="<%= request.getContextPath() %>/logout">Logout</a>

    <h3>All Categories</h3>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Created By</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td><c:out value="${category.id}" /></td>
                    <td><c:out value="${category.name}" /></td>
                    <td><c:out value="${category.user.username}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>