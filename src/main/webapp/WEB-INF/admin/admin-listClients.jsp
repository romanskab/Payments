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
<a href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a><span>  </span>
<a href="?sessionLocale=ua"><fmt:message key="label.lang.ua"/></a>
<br><br>

<b><fmt:message key="label.Clients"/>:</b>
<table>
    <thead>
    <tr>
        <th>№</th>
        <th><fmt:message key="label.Name"/></th>
        <th><fmt:message key="label.state"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td><c:out value="${client.id}"/></td>
            <td><c:out value="${client.firstname}"/> <c:out value="${client.lastname}"/></td>
            <td><c:out value="${client.state}"/></td>
            <td>
                <c:if test="${client.state == 'UNBLOCKED'}">
                    <form action="${pageContext.request.contextPath}/admin/clients/block">
                        <input hidden name="client-id" value="${client.id}">
                        <button>block</button>
                    </form>
                </c:if>
                <c:if test="${client.state == 'BLOCKED'}">
                    <form action="${pageContext.request.contextPath}/admin/clients/unblock">
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
