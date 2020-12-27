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
    <title>Client</title>
</head>
<body>
<a href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a><span>  </span>
<a href="?sessionLocale=ua"><fmt:message key="label.lang.ua"/></a>

<h3><fmt:message key="label.ClientPage"/><br>
    <fmt:message key="label.Name"/>: ${user.firstname} ${user.lastname}
</h3>

<br>
<a href="${pageContext.request.contextPath}/client/accounts"><fmt:message key="label.Accounts"/></a>

<br><br>
<a href="${pageContext.request.contextPath}/client/payments"><fmt:message key="label.Payments"/></a>

<br><br>
<a href="${pageContext.request.contextPath}/client/cards"><fmt:message key="label.Cards"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>

</body>
<style>
    body {
        margin: 0;
        background-color: lightgray;
        text-align: center;
    }
</style>
</html>
