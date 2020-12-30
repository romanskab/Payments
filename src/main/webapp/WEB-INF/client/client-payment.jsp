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
<a href="?sessionLocale=en&account-id=${account.id}"><fmt:message key="label.lang.en"/></a><span>  </span>
<a href="?sessionLocale=ua&account-id=${account.id}"><fmt:message key="label.lang.ua"/></a>
<br><br>
<fmt:message key="label.account"/>: ${account.id}
<br>
<fmt:message key="label.balance"/>: ${account.balance}
<br><br>

<form action="${pageContext.request.contextPath}/client/payments/new" method="post">
    <b><fmt:message key="label.NewPayment"/></b>
    <input hidden name="account-id" value="${account.id}">
    <br><br>
    <fmt:message key="label.Sum"/>: <input name="sum" type="number" min="0" step="0.01"/>
    <br><br>
    <fmt:message key="label.Comment"/>: <input name="comment" rows="2" required/>
    <br><br>
    <button>
        <fmt:message key="label.Create"/>
    </button>
</form>

<br><br>
<a href="${pageContext.request.contextPath}/client/accounts"><fmt:message key="label.Accounts"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>
</body>
</html>
