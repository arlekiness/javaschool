<%--
  Created by IntelliJ IDEA.
  User: belo4ka_new
  Date: 28.08.2018
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form class="form" action="/login" method="post">
    <input name="login" id="userNameUser" type="email" placeholder="login" required/>
    <input name="password" id="passwordUser" type="password" placeholder="password" required/>
    <button id="loginUser">Sign in</button>
    <p class="message">Not registered? <a href="/registration">Create an account</a></p>
</form>
</body>
</html>
