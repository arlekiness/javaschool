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
<p class="message">Not registered? <a href="/logout">Log Out</a></p>
</body>
</html>