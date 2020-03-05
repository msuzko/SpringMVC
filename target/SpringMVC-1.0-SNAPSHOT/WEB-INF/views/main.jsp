<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <title>Home</title>
</head>

<body>
    <p><spring:message code="hello"/>, ${user.name}!</p>
    <p><spring:message code="your.password"/> ${user.password}!</p>
    <p><spring:message code="you.admin"/> ${user.admin}!</p>
    <p>${locale}</p>
</body>
</html>
