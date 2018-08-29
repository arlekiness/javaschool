<%--
  Created by IntelliJ IDEA.
  User: Bystrov
  Date: 29.08.2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Station</title>
</head>
<body>
<form class="form" action="/addStationPlus" method="post">
    Station
    <input name="name" type="text"/><br/>
    Color
    <input name="color" type="text"/><br/>
    <input name="Add" type="submit"/>
</form>
</body>
</html>
