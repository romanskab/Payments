<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body style="margin: 0; background-color: lightgray; text-align: center">
<form action="${pageContext.request.contextPath}/login" method="post">
    <b>Login</b>
    <br><br>
    <input type="radio" name="role" value="ROLE_ADMIN"/>admin
    <input type="radio" name="role" value="ROLE_CLIENT" checked/>client
    <br><br>
    Username: <input name="username"/>
    <br><br>
    Password: <input name="password" type="password" min=1/>
    <br><br>
    <input type="submit" value="Submit"/>
</form>

<br/>
<a href="${pageContext.request.contextPath}/logout">На головну</a>

</body>
</html>
