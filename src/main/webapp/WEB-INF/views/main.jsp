<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Home</title>
</head>

<body>
    <p><spring:message code="hello"/>, ${user.name}!</p>
    <p><spring:message code="your.password"/> ${user.password}!</p>
    <p><spring:message code="you.admin"/> ${user.admin}!</p>
    <p>${locale}</p>

    <form:form
            method="POST"
            action="uploadFile"
            enctype="multipart/form-data"
            modelAttribute="uploadedFile">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file"/></td>
                <td style="color: red; font-style: italic;">
                    <form:errors path="file"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"></td>
                <td></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
