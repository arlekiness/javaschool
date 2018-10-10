<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - Admin panel - Stations</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="shortcut icon" href="/static/images/sw.png" type="image/png">


    <!-- css -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap2.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/style-dash2.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/vypad-spiski-dlya-form.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/sweetalert2.css">
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
    <script src="/static/js/sweetalert2.js"></script>


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
        <p class="hero-text text-center">Admin panel</p>

        <div class="korpus">
            <div class="train-button">
                <div class="train-icon"></div>
                <a href="/dashtrain" class="train-label">Trains</a></div>
            <div class="station-button">
                <div class="station-icon"></div>
                <a href="/dashstation" class="station-label">Stations</a></div>


            <main>
                <table>
                    <thead>
                    <tr>
                        <th>
                            STATION
                        </th>
                        <th>
                            STATUS
                        </th>
                        <th>

                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${model.stations}" var="station">
                        <tr class="table-first">
                            <td data-title='STATION'><span><em class="fa fa-circle"></em> ${station.getName()}</span></td>
                            <c:if test="${station.getStatus().getStatusName() eq 'WORKED'}">
                                <td data-title='STATUS'><a class='button open' href="/closeStation/${station.getName()}"
                                                           data-type="modal-trigger">${station.getStatus().getStatusName()}</a>
                                </td>
                            </c:if>
                            <c:if test="${station.getStatus().getStatusName() eq 'CLOSED'}">
                                <td data-title='STATUS'><a class='button closed'
                                                           href="/openStation/${station.getName()}"
                                                           data-type="modal-trigger">${station.getStatus().getStatusName()}</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </main>

            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach begin="1" end="${model.stationPages}" step="1" varStatus="loopSt">
                        <li class="page-item"><a class="page-link"
                                                 href="/dashstation/${loopSt.count}">${loopSt.count}</a></li>
                    </c:forEach>
                </ul>
            </nav>


        </div>


    </div>


    <!-- конец контента -->


    <div class="clearfix"></div>


</div>


</body>
</html>
