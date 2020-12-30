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
<br><br>
<b><fmt:message key="label.Accounts"/>:</b>

<table>
    <thead>
    <tr>
        <th><fmt:message key="label.account"/></th>
        <th><fmt:message key="label.balance"/></th>
        <th><fmt:message key="label.state"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td><a href="${pageContext.request.contextPath}/client/accounts?account-id=${account.id}"><c:out
                    value="${account.id}"/></a></td>
            <td><c:out value="${account.balance}"/></td>
            <td><c:out value="${account.state}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br><br>
<form action="${pageContext.request.contextPath}/client/accounts/create">
    <button>
        <fmt:message key="label.CreateNewAccount"/>
    </button>
</form>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>
</body>
</html>
