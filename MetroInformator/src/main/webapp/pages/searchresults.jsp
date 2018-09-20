<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - Tickets</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="static/images/sw.png" type="image/png">


    <!-- css -->
    <link href="static/css/bootstrap2.min.css" rel="stylesheet" />
    <link href="static/css/style-tickets-table.css" rel="stylesheet" />
    <link href="static/css/vypad-spiski-dlya-form.css" rel="stylesheet" />
    <!-- ==================================================
                   javascript
================================================== -->
    <script src="static/js/modernizr.js"></script> <!-- Modernizr -->
    <script src="static/js/jquery-3.2.1.js"></script>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>
    <script src='http://code.angularjs.org/1.3.14/angular-animate.js'></script>
    <script src="static/js/main.js"></script> <!-- Resource jQuery -->
    <script src="static/js/custom.js"></script>
    <script src="static/js/velocity.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/jcanvas.js"></script>




</head>

<body>
<div id="site-content" class="tickets-page">
    <div class="all-menu navbar-fixed-top">

        <!-- Основное меню -->
        <header class="header-tickets">
            <div class="navbar">
                <div class="container">
                    <a class="navbar-brand" href="/"><i class="icon-logo"></i></a>
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
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle user-avatar"><span class="avatarka"><i class="fa fa-user-circle-o"></i></span>John Doe <i class="fa fa-caret-down"></i></a>
                                <ul class="dropdown-menu dash-user">
                                    <li><a href="#">My tickets</a></li>
                                    <br>
                                    <li><a href="#">Dashboard</a></li>
                                    <br>
                                    <li><a href="index.html">Log out</a></li>
                                </ul>
                            </li>
                        </ul>

                    </div>


                    <!-- гамбургерное меню  -->

                    <div class="ham-menu">
                        <input id="hamburger" class="hamburger" type="checkbox"/>
                        <label class="hamburger" for="hamburger">
                            <i></i>
                        </label>
                        <div class="drawer-list">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li class="active"><a href="tickets.html">Tickets</a></li>
                                <li><a href="shedule.html">Shedule</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>
    <!-- конец основного меню -->

    <!-- контент -->

    <c:set var="count" value="${0}" scope="page"/>
    <div class="wrapper-tickets">
        <p class="hero-text text-center">Search for tickets</p>


        <main>
            <table>
                <thead>
                <tr>
                    <th>
                        DEPARTURE
                    </th>
                    <th>
                        ARRIVAL
                    </th>
                    <th>
                        TRAIN
                    </th>
                    <th>
                        TRANSITIONS
                    </th>
                    <th>
                        TICKETS
                    </th>
                </tr>
                </thead>
<c:if test = "${not empty TicketList}">
    <c:forEach items="${TicketList}" var="list">
                <tbody>
                        <tr class="table-first">
                            <td data-title='DEPARTURE'>
                                    ${list.get(0).getTicketDateDeparture().toString().substring(11, 16)} <span><i class="fa fa-circle"></i> ${list.get(0).getStationBegin().getName()}</span>
                            </td>
                            <td data-title='ARRIVAL'>
                                    ${list.get(list.size() - 1).getTicketDateArrival().toString().substring(11, 16)}<span><i class="fa fa-circle"></i> ${list.get(list.size() - 1).getStationEnd().getName()}</span>
                            </td>
                            <td data-title='TRAIN'>
                                    ${list.get(0).getTrain().getTrainName()}
                            </td>
                            <td class="transitions" data-title='TRANSITIONS' data-toggle="collapse" data-target="#${count}">
                                    ${list.size() - 1}
                            </td>
                            <td class='select'>
                                <div class="cd-section">
                                    <div class="cd-modal-action">
                                        <a class='button' href="#0" data-type="modal-trigger">BUY TICKETS</a>
                                        <span class="cd-modal-bg"></span>
                                    </div> <!-- cd-modal-action -->
                                    <div class="cd-modal">
                                        <div class="cd-modal-content">
                                            <p>Спасибо за покупку, вы молодец (нет)</p>
                                        </div> <!-- cd-modal-content -->
                                    </div> <!-- cd-modal -->
                                    <a href="#0" class="cd-modal-close">Скрыть</a>
                                </div>
                            </td>
                        </tr>
                </tbody>


                <tbody class="table-second collapse" id="${count}">
                    <tr><td data-title='DEPARTURE'>
                        9:50 <span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span>
                    </td>
                    <td data-title='ARRIVAL'>
                        10:00 <span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span>
                    </td>
                    <td data-title='TRAIN'>
                        T-001
                    </td>
                    <td class="transitions" data-title='TRANSITIONS'>
                        000
                    </td>
                    <td data-title='TICKETS'>
                    </td>
                </tr>

                        <tr>
                            <td data-title='DEPARTURE'>
                                9:50 <span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span>
                            </td>
                            <td data-title='ARRIVAL'>
                                10:00 <span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span>
                            </td>
                            <td data-title='TRAIN'>
                                T-001
                            </td>
                            <td class="transitions" data-title='TRANSITIONS'>
                                000
                            </td>
                            <td data-title='TICKETS'>
                            </td>
                        </tr>


                        <c:set var="count" value="${count + 1}" scope="page"/>
                </tbody>
    </c:forEach>
</c:if>
            </table>












        </main>



    </div>



    <!-- конец контента -->


    <div class="clearfix"></div>




</div>




</body>
</html>
