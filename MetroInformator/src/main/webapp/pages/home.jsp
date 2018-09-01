<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags/>
</head>
<body>
Welcome to hell

    <sec:authorize access="hasRole('ROLE_USER')">
        Userishka
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        Adminishka
    </sec:authorize> </br>
    <a href="/logout">Log Out</a></p>

    <form action="/stationList" method="GET">
        <select name="stationSelect">
            <option value="Technologicheskii institut">Technologicheskii institut</option>
            <option value="Baltiiskaya">Baltiiskaya</option>
            <option value="Frunzenskaya">Frunzenskaya</option>
            <option value="Sennaya ploschad">Sennaya ploschad</option>
            <option value="Pushkinskaya">Pushkinskaya</option>
        </select>
        <input type="submit" value="Search" />
    </form>

</body>
</html>