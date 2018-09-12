<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>Registration form</title>
    </head>
    <body>

    <form action= "/registration" method= "POST">

        <p>First Name: </p><p> <input type= "text" name= "firstName"> </p>

        <p>Last Name: </p><p> <input type= "text" name= "lastName"></p>

        <p>E-mail: </p><p> <input type= "text" name= "login"></p>

        <p>Password: </p><p> <input type= "password" name= "password"></p>

        <input type= "submit" value= "Отправить">
    </form><br/>
    <c:if test="${not empty UserControllerException}">
        ${UserControllerException.getError()}
    </c:if>

    </body>
</html>
