<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>List of Students</title>

</head>


<body>
<h2>List of Stations</h2>
<table>
    <tr>
        <td>NAME</td><td>COLOR</td>
    </tr>
    ${stations}
    <c:forEach items="${station}" var="station">
        <tr>
            <td>${station.name}</td>
            <td>${station.color}</td>
        </tr>
    </c:forEach>
</table>
<br/>
</body>
</html>
