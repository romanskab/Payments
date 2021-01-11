<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body style="margin: 0; background-color: lightgray; text-align: center">

<a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a><span>  </span>
<a href="?sessionLocale=ua"><fmt:message key="label.lang.ua" /></a>

<h1>payments.ua</h1>
<br>
<h4>
    <fmt:message key="label.select"/>
</h4>
<form action="${pageContext.request.contextPath}/login">
    <button>
        <fmt:message key="label.login"/>
    </button>
</form>
<br>
<form action="${pageContext.request.contextPath}/registration">
    <button>
        <fmt:message key="label.register"/>
    </button>
</form>

<h:footer></h:footer>
</body>
</html>
