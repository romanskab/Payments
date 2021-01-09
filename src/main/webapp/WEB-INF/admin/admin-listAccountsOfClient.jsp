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
<a href="?sessionLocale=en&client-id=${client.id}"><fmt:message key="label.lang.en"/></a><span>  </span>
<a href="?sessionLocale=ua&client-id=${client.id}"><fmt:message key="label.lang.ua"/></a>
<br><br>
<b><fmt:message key="label.Client"/>:</b> ${client.firstname} ${client.lastname}
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
            <td><c:out value="${account.id}"/></td>
            <td><c:out value="${account.balance}"/></td>
            <td><c:out value="${account.state}"/></td>
            <td>
                <c:if test="${account.state == 'UNBLOCKED'}">
                    <form action="${pageContext.request.contextPath}/admin/clients/account/block">
                        <input hidden name="account-id" value="${account.id}">
                        <input hidden name="client-id" value="${client.id}">
                        <button>block</button>
                    </form>
                </c:if>
                <c:if test="${account.state == 'BLOCKED'}">
                    <form action="${pageContext.request.contextPath}/admin/clients/account/unblock">
                        <input hidden name="account-id" value="${account.id}">
                        <input hidden name="client-id" value="${client.id}">
                        <button>unblock</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br><br>
<a href="${pageContext.request.contextPath}/admin"><fmt:message key="label.AdminPage"/></a>

<br><br><br>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout"/></a>
</body>
</html>
