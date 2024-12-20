<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student List</title>
</head>
<body>
<h1>Student List</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Password</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${students}" var="student">
    <tr>
      <td>${student.id}</td>
      <td>${student.username}</td>
      <td>${student.password}</td>
      <td>
        <a href="${pageContext.request.contextPath}/student/edit?id=${student.id}">Edit</a>
        <a href="${pageContext.request.contextPath}/student/delete?id=${student.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="${pageContext.request.contextPath}/student/new">Add New Student</a>
</body>
</html>