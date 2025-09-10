<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>
    <h2>Welcome, Admin!</h2>
    <p>Hello, <c:out value="${sessionScope.loggedInUser.username}" /> (Role: Admin)</p>
    <a href="<%= request.getContextPath() %>/logout">Logout</a>

    <h3>All Categories</h3>
    <a href="<%= request.getContextPath() %>/admin/category/add">Add New Category (Admin)</a><br><br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Created By</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td><c:out value="${category.id}" /></td>
                    <td><c:out value="${category.name}" /></td>
                    <td><c:out value="${category.user.username}" /></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/admin/category/edit?id=<c:out value="${category.id}" />">Edit</a> |
                        <a href="<%= request.getContextPath() %>/admin/category/delete?id=<c:out value="${category.id}" />" onclick="return confirm('Are you sure you want to delete this category?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>