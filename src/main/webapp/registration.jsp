<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body style="margin: 0; background-color: lightgray; text-align: center">

<form action="${pageContext.request.contextPath}/registration" method="post">
    <b>Client registration</b>
    <br><br>
    Name: <input name="firstName"/>
    <br><br>
    Surname: <input name="lastName"/>
    <br><br>

    Email: <input name="username"/>
    <br><br>
    Password: <input name="password" type="password" min=1/>
    <br><br>
    Confirm password: <input name="confirmPassword" type="password" min=1/>
    <br><br>
    <input type="submit" value="Submit"/>
</form>

<br>
<a href="${pageContext.request.contextPath}/index.jsp">Index</a>

</body>
</html>
