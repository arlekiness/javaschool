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
    <link href="/static/css/style-tickets-table.css" rel="stylesheet"/>
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
                            <li class="active"><a href="/tickets">Tickets</a></li>
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


                        <!-- гамбургерное меню  -->

                        <%----%>
                    </div>
                </div>
        </header>
    </div>
    <!-- конец основного меню -->

    <!-- контент -->

    <c:set var="count" value="${0}" scope="page"/>
    <div class="wrapper-tickets">
        <p class="hero-text text-center">Search for tickets</p>


    </div>

    <div class="container table">
        <div class="row thead">
            <div class="col-md-3">DEPARTURE</div>
            <div class="col-md-3">ARRIVAL</div>
            <div class="col-md-2">TRAIN</div>
            <div class="col-md-2">TRANSITIONS</div>
            <div class="col-md-2">TICKETS</div>
        </div>
        <c:if test="${not empty TicketList}">
            <c:forEach items="${TicketList}" var="list">
                <div class="row line" data-toggle="collapse" data-target="#${count}">
                    <div class="col-md-3 time">
                            ${list.get(0).getTicketDateDeparture().toString().substring(11, 16)} <span><em
                            class="fa fa-circle"></em> ${list.get(0).getStationBegin().getName()}</span></div>
                    <div class="col-md-3 time">${list.get(list.size() - 1).getTicketDateArrival().toString().substring(11, 16)}<span><em
                            class="fa fa-circle"></em> ${list.get(list.size() - 1).getStationEnd().getName()}</span>
                    </div>
                    <div class="col-md-2 time">${list.get(0).getTrain().getTrainName()}</div>
                    <div class="col-md-2"><span class="transitions">${list.size() - 1} TRANSITION</span></div>
                    <div class="col-md-2 buy-ticket"><a class='button' href="/registerTickets/${count}"
                                                        data-type="modal-trigger">BUY TICKETS</a></div>


                </div>

                <div class="container collapse" id="${count}">
                    <c:forEach items="${list}" var="ticket">
                        <div class="row line" style="background-color:#edeef2">
                            <div class="col-md-3 time">
                                    ${ticket.getTicketDateDeparture().toString().substring(11, 16)} <span><em
                                    class="fa fa-circle"></em> ${ticket.getStationBegin().getName()}</span></div>
                            <div class="col-md-3 time">${ticket.getTicketDateArrival().toString().substring(11, 16)}
                                <span><em class="fa fa-circle"></em> ${ticket.getStationEnd().getName()}</span></div>
                            <div class="col-md-2 time">${ticket.getTrain().getTrainName()}</div>
                            <div class="col-md-2"></div>
                            <div class="col-md-2 buy-ticket"></div>
                        </div>
                    </c:forEach>
                </div>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
        </c:if>

    </div>

</div>


<!-- конец контента -->


<div class="clearfix"></div>


</body>
</html>