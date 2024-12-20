<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<form action="${pageContext.request.contextPath}/student/save" method="POST">
    <c:if test="${not empty student.id}">
        <input type="hidden" name="id" value="${student.id}" />
    </c:if>
    <label for="username">Username:</label>
    <input type="text" name="username" value="${student.username}" />
    <br>
    <label for="password">Password:</label>
    <input type="password" name="password" value="${student.password}" />
    <br>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/student/list">Cancel</a>
</form>
</body>
</html>