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
    <title>Admin</title>
</head>
<body>
<a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a><span>  </span>
<a href="?sessionLocale=ua"><fmt:message key="label.lang.ua" /></a>

<h4>Admin home page</h4>
<h3>Name: ${user.firstname} ${user.lastname}</h3>

<br>
<a href="${pageContext.request.contextPath}/admin/list-clients">List clients</a>

<br><br>
<a href="${pageContext.request.contextPath}/admin/list-requests">List requests</a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
<style>
    body{
        margin: 0;
        background-color: lightgray;
        text-align: center;
    }
</style>
</html>
