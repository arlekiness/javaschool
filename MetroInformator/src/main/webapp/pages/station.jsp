<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>List of Stations</title>

</head>


<body>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>Station</th>
            <th>Arrival Date</th>
            <th>Departure Date</th>
            <th>Train Number</th>
            <th>Path</th>
        </tr>
            <c:forEach items="${scheduleList}" var="list">
            <tr><td>${list.getStation()}</td><td>${list.dateArrival}</td><td>${list.dateDeparture}</td><td>${list.trainName}</td><td>${list.endPointStationName}</td></tr>
            </c:forEach>
    </table><br/>
    <a href="/home">Back</a></p>
</body>
</html>
