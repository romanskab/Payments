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

<b><fmt:message key="label.Payments"/>:</b>
<table>
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="label.Status"/></th>
        <th><fmt:message key="label.Sum"/></th>
        <th><fmt:message key="label.Comment"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="payment" items="${payments}">
        <form action="${pageContext.request.contextPath}/client/payments/send" method="post">
            <tr>
                <td><c:out value="${payment.id}"/></td>
                <td><c:out value="${payment.status}"/></td>
                <td><c:out value="${payment.sum}"/></td>
                <td><c:out value="${payment.comment}"/></td>
                <td><c:if test="${payment.status != 'PAID'}">
                    <input hidden name="payment-id" value="${payment.id}">
                    <button><fmt:message key="label.send"/></button>
                </c:if></td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>

<br><br>
<a href="${pageContext.request.contextPath}/client/accounts"><fmt:message key="label.Accounts"/></a>
<br><br>
<a href="${pageContext.request.contextPath}/client"><fmt:message key="label.ClientPage"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>
</body>
</html>
