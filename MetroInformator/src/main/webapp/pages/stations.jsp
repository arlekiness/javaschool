<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>List of Stations</title>

</head>


<body>
<h2>List of Stations</h2>
<table>
    <tr>
        <td>NAME</td><td>COLOR</td>
    </tr>
    <c:forEach items="${station}" var="station">
        <tr>
            <td>${station.name}</td>
            <td>${station.color}</td>
            <td><a href="<c:url value="/deleteStation">
		                 <c:param name="name" value="${station.name}" /></c:url>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="/addStation">Add Station</a>
</body>
</html>
