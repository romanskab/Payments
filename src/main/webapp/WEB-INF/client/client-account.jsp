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
<br>
<fmt:message key="label.state"/>: ${account.state}
<br><br>
<fmt:message key="label.Cards"/>:

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
                <td><a href="${pageContext.request.contextPath}/client/accounts/cards?card-id=${card.id}"><c:out value="${card.id}"/></a></td>
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

</body>
</html>
