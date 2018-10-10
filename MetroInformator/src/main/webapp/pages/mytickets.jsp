<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - Tickets</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="shortcut icon" href="/static/images/sw.png" type="image/png">


    <!-- css -->
    <link href="/static/css/bootstrap2.min.css" rel="stylesheet"/>
    <link href="/static/css/style-my-tickets.css" rel="stylesheet"/>
    <link href="/static/css/vypad-spiski-dlya-form.css" rel="stylesheet"/>
    <!-- ==================================================
                   javascript
================================================== -->
    <script src="/static/js/modernizr.js"></script> <!-- Modernizr -->
    <script src="/static/js/jquery-3.2.1.js"></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>
    <script src='http://code.angularjs.org/1.3.14/angular-animate.js'></script>
    <script src="/static/js/main.js"></script> <!-- Resource jQuery -->
    <script src="/static/js/custom.js"></script>
    <script src="/static/js/velocity.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/jcanvas.js"></script>


</head>

<body>
<div id="site-content" class="tickets-page">
    <div class="all-menu navbar-fixed-top">

        <!-- Основное меню -->
        <header class="header-tickets">
            <div class="navbar">
                <div class="container">
                    <a class="navbar-brand" href="/"><em class="icon-logo"></em></a>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/">Home</a></li>
                            <li><a href="/tickets">Tickets</a></li>
                            <li><a href="/schedule">Schedule</a></li>
                        </ul>
                    </div>
                    <!-- КНОПКИ -->

                    <div class="register-signin-tickets pull-right main-nav1">

                        <ul>
                            <li class="sign-out dropdown">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle user-avatar"><span
                                        class="avatarka"><em class="fa fa-user-circle-o"></em></span><sec:authentication
                                        property="principal.username"/> <em class="fa fa-caret-down"></em></a>
                                <ul class="dropdown-menu dash-user">
                                    <li><a href="/myTickets">My tickets</a></li>
                                    <br>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <li><a href="/dashtrain">Admin Panel</a></li>
                                        <br>
                                    </sec:authorize>

                                    <li><a href="/logout">Log out</a></li>
                                </ul>
                            </li>
                        </ul>

                    </div>

                    <!-- гамбургерное меню  -->

                    <%----%>
                </div>
            </div>
        </header>
    </div>
    <!-- конец основного меню -->

    <!-- контент -->

    <div class="wrapper-tickets">
        <p class="hero-text text-center">My tickets</p>
        <div class="hero-underline">
            <span class="hero-station">${loggedUser.getFirstName()} ${loggedUser.getLastName()}</span>
        </div>
        <main>
            <c:if test="${empty myTicketList}">
                <div class="hero-underline1">
                    <span class="hero-station1">There is no tickets yet</span>
                </div>
            </c:if>
            <c:if test="${not empty myTicketList}">
                <table>
                    <thead>
                    <tr>
                        <th>TRAIN №</th>
                        <th>DEPARTURE</th>
                        <th>ARRIVAL</th>
                        <th>PRICE</th>
                        <th>VALID</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${myTicketList}" var="list">
                        <tr class="table-first">
                            <td data-title='TRAIN'>${list.getTrain().getTrainName()}</td>
                            <td data-title='DEPARTURE'>${list.getTicketDateDeparture().toString().substring(0, 16)}<span><em
                                    class="fa fa-circle"></em> ${list.getStationBegin().getName()} </span></td>
                            <td data-title='ARRIVAL'>${list.getTicketDateArrival().toString().substring(0, 16)}<span><em
                                    class="fa fa-circle"></em> ${list.getStationEnd().getName()}</span></td>
                            <td data-title='PRICE'>500</td>
                            <td data-title='VALID' class="valid">${list.getValid()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </main>
    </div>
    <!-- конец контента -->
    <div class="clearfix"></div>
</div>

</body>
</html>
