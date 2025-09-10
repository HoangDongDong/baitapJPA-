<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Home</title>
</head>
<body>
    <h2>Welcome, Manager!</h2>
    <p>Hello, <c:out value="${sessionScope.loggedInUser.username}" /> (Role: Manager)</p>
    <a href="<%= request.getContextPath() %>/logout">Logout</a>

    <h3>My Categories</h3>
    <a href="<%= request.getContextPath() %>/manager/category/add">Add New Category</a><br><br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td><c:out value="${category.id}" /></td>
                    <td><c:out value="${category.name}" /></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/manager/category/edit?id=<c:out value="${category.id}" />">Edit</a> |
                        <a href="<%= request.getContextPath() %>/manager/category/delete?id=<c:out value="${category.id}" />" onclick="return confirm('Are you sure you want to delete this category?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>