<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Buying Ticket</title>
</head>
<body>
    <c:if test = "${empty stationList}">
        Sorry, there is no tickets
    </c:if>
    <c:if test = "${not empty stationList}">
        <table border="1" width="100%" cellpadding="5">
            <tr><td>Stations</td><td>Buy Ticket</td></tr>
                <c:forEach items="${stationList}" var="list">
                    <tr><td>${list}</td><td><a href="/buyTicketFinal/${list.toString()}">Buy Ticket</a></td></tr>
                </c:forEach>
        </table>
    </c:if>

</body>
</html>
