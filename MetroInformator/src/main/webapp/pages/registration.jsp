<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    </form>
    </body>
</html>
