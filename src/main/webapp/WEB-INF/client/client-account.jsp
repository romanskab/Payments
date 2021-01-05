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
<b><fmt:message key="label.account"/></b>: ${account.id}
<br>
<b><fmt:message key="label.balance"/></b>: ${account.balance}
<br>
<b><fmt:message key="label.state"/></b>: ${account.state}
<br>
<b><a href="${pageContext.request.contextPath}/client/payments?account-id=${account.id}"><fmt:message
        key="label.Payments"/></a></b>
<br><br>
<form action="${pageContext.request.contextPath}/client/accounts/replenishment">
    <input hidden type="number" name="account-id" value="${account.id}">
    <button>
        <fmt:message key="label.Replenishment"/>
    </button>
</form>

<br>
<c:if test="${account.state == 'UNBLOCKED'}">
    <form action="${pageContext.request.contextPath}/client/accounts/block">
        <input hidden name="account-id" value="${account.id}">
        <button>
            <fmt:message key="label.BlockAccount"/>
        </button>
    </form>
</c:if>
<c:if test="${account.state == 'BLOCKED'}">
    <form action="${pageContext.request.contextPath}/client/accounts/unblock">
        <input hidden name="account-id" value="${account.id}">
        <button>
            <fmt:message key="label.RequestToUnblock"/>
        </button>
    </form>
</c:if>

<br><br>
<b><fmt:message key="label.Cards"/></b>:

<table>
    <thead>
    <tr>
        <th><fmt:message key="label.card"/></th>
        <th><fmt:message key="label.state"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="card" items="${cards}">
        <tr>
            <td><a href="${pageContext.request.contextPath}/client/cards?card-id=${card.id}"><c:out
                    value="${card.id}"/></a></td>
            <td><c:out value="${card.state}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br><br>
<form action="${pageContext.request.contextPath}/client/cards/create" method="post">
    <input hidden type="number" name="account-id" value="${account.id}">
    <button>
        <fmt:message key="label.OrderNewCard"/>
    </button>
</form>

<br><br>
<a href="${pageContext.request.contextPath}/client/accounts"><fmt:message key="label.Accounts"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/client"><fmt:message key="label.ClientPage"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>
</body>
</html>
