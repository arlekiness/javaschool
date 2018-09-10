<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Buying Ticket</title>
</head>
<body>
    <c:if test = "${empty TicketList}">
        Sorry, there is no tickets
    </c:if>
    <c:if test = "${not empty TicketList}">
        <table border="1" width="100%" cellpadding="5">
            <tr><td>Stations begin</td><td>Station End</td><td>Date departure</td><td>Date arrival</td><Transition></tr>
                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${TicketList}" var="list">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr><td>${count}</td>
                        <td>${list.get(0).getStationBegin().getName()}</td>
                        <td>${list.get(list.size() - 1).getStationEnd().getName()}</td>
                        <td>${list.get(0).getTicketDateDeparture()}</td>
                        <td>${list.get(list.size() - 1).getTicketDateArrival()}</td>
                        <td>${list.size() - 1}</td>
                        <td><a href="/requestTicket/${count}">Buy</a></td>
                </c:forEach>
        </table>
    </c:if>

</body>
</html>
