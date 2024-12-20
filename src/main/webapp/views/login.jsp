<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/login" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" /><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" /><br>
  <input type="submit" value="Login" />
</form>
</body>
</html>