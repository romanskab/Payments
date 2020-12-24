<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Register</title>
</head>
<body style="margin: 0; background-color: lightgray; text-align: center">

<a href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a><span>  </span>
<a href="?sessionLocale=ua"><fmt:message key="label.lang.ua"/></a>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <h4><fmt:message key="label.ClientRegistration"/></h4>
    <br><br>
    <fmt:message key="label.Name"/>: <input name="firstName"/>
    <br><br>
    <fmt:message key="label.Surname"/>: <input name="lastName"/>
    <br><br>

    <fmt:message key="label.Email"/>: <input name="username"/>
    <br><br>
    <fmt:message key="label.Password"/>: <input name="password" type="password" min=1/>
    <br><br>
    <input type="submit" value="<fmt:message key="label.register"/>"/>
</form>

<br>
<a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="label.StartPage"/></a>

</body>
<style>
    body {
        margin: 0;
        background-color: lightgray;
        text-align: center;
        padding-top: 10px;
    }
</style>
</html>
