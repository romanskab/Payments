<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Login</title>
</head>
<body>
<a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a><span>  </span>
<a href="?sessionLocale=ua"><fmt:message key="label.lang.ua" /></a>

<form action="${pageContext.request.contextPath}/login" method="post">
    <h4><fmt:message key="label.login"/></h4>
    <br>
    <fmt:message key="label.Username"/>: <input name="username"/>
    <br><br>
    <fmt:message key="label.Password"/>: <input name="password" type="password" min=1/>
    <br><br>
    <button>
        <fmt:message key="label.login"/>
    </button>
</form>

<br/><br>
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
