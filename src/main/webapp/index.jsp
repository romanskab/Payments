<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body style="margin: 0; background-color: lightgray; text-align: center">
<h1>Payments.ua</h1>
<br>
<h4>Please, select next step:</h4>
<form action="${pageContext.request.contextPath}/login">
    <button>Login</button>
</form>
<br>
<form action="${pageContext.request.contextPath}/registration">
    <button>Register</button>
</form>

<br/>
<a href="${pageContext.request.contextPath}/login">Login</a>
<br/>
<a href="${pageContext.request.contextPath}/registration">Registration form</a>
<br>
<a href="${pageContext.request.contextPath}/exception">Exception</a>

</body>
</html>
