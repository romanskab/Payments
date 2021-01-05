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
<a href="?sessionLocale=en&account-id=${account.id}&card-id=${card.id}"><fmt:message key="label.lang.en"/></a><span>  </span>
<a href="?sessionLocale=ua&account-id=${account.id}&card-id=${card.id}"><fmt:message key="label.lang.ua"/></a>
<br><br>

<b><fmt:message key="label.card"/></b>: ${card.id}
<br>
(<fmt:message key="label.account"/>: ${card.account.id})
<br>
<b><fmt:message key="label.balance"/></b>: ${card.account.balance}
<br>
<b><fmt:message key="label.state"/></b>: ${card.state}

<br><br>
<b><fmt:message key="label.Payments"/></b>:

<br><br>
<a href="${pageContext.request.contextPath}/client/accounts"><fmt:message key="label.Accounts"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/client"><fmt:message key="label.ClientPage"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>

</body>
</html>
