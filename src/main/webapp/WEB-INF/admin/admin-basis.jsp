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

<h3><fmt:message key="label.AdminPage"/><br>
    <fmt:message key="label.Name"/>: ${user.firstname} ${user.lastname}
</h3>

<br>
<a href="${pageContext.request.contextPath}/admin/clients"><fmt:message key="label.Clients"/></a>

<br><br>
<a href="${pageContext.request.contextPath}/admin/requests"><fmt:message key="label.Requests"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>
</body>
<style>
    body{
        margin: 0;
        background-color: lightgray;
        text-align: center;
    }
</style>
</html>
