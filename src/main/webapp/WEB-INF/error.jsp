<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h2>
    Error Page<br/>
    <i>Error <%= exception %></i>
</h2>
<br>
<a href="${pageContext.request.contextPath}/index.jsp">Index</a>

</body>
</html>
