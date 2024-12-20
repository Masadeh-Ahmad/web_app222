<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Details</title>
</head>
<body>
<h1>Student Details</h1>
<table>
  <tr>
    <th>ID</th>
    <td>${student.id}</td>
  </tr>
  <tr>
    <th>Username</th>
    <td>${student.username}</td>
  </tr>
  <tr>
    <th>Password</th>
    <td>${student.password}</td>
  </tr>
</table>
<a href="${pageContext.request.contextPath}/student/edit?id=${student.id}">Edit</a>
<a href="${pageContext.request.contextPath}/student/list">Back to List</a>
</body>
</html>