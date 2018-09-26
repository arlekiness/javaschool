<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - Admin panel - Trains</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/sw.png" type="image/png">


    <!-- css -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap2.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/style-dash1.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/vypad-spiski-dlya-form.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/sweetalert2.css">
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
    <script src="/static/js/sweetalert2.js"></script>


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
                            <li><a href="/tickets">Tickets</a></li>
                            <li><a href="/schedule">Schedule</a></li>
                        </ul>
                    </div>


                    <!-- КНОПКИ -->

                    <div class="register-signin-tickets pull-right main-nav1">

                        <ul>
                            <li class="sign-out dropdown">
                                <a href="#" data-toggle="dropdown" class="dropdown-toggle user-avatar"><span
                                        class="avatarka"><i class="fa fa-user-circle-o"></i></span><sec:authentication
                                        property="principal.username"/> <i class="fa fa-caret-down"></i></a>
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

                    <div class="ham-menu">
                        <input id="hamburger" class="hamburger" type="checkbox"/>
                        <label class="hamburger" for="hamburger">
                            <i></i>
                        </label>
                        <div class="drawer-list">
                            <ul>
                                <li><a href="/">Home</a></li>
                                <li><a href="/tickets">Tickets</a></li>
                                <li><a href="/schedule">Schedule</a></li>
                            </ul>
                        </div>
                    </div>
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
                <div class="add-train">
                    <a class='button1' href="/createtrain" data-type="modal-trigger">Add new train</a></div>
                <table>
                    <thead>
                    <tr>
                        <th>
                            TRAIN ID
                        </th>
                        <th>
                            TRAIN №
                        </th>
                        <th>
                            CAPACITY
                        </th>
                        <th>
                            STATUS
                        </th>
                        <th>

                        </th>
                        <th>

                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${model.trains}" var="train">
                        <tr class="table-first">
                            <td data-title='TRAIN-ID'>${train.getId()}</td>
                            <td data-title='TRAIN-NO'>${train.getTrainName()}</td>
                            <td data-title='CAPACITY'>${train.getCapacity()}</td>
                            <td data-title='STATUS'><span><i
                                    class="fa fa-circle"></i> ${train.getStatus().getStatusName()}</span></td>
                            <td data-title='DELETE'><a class='button' href="/deleteTrain/${train.getId()}"
                                                       data-type="modal-trigger">DELETE</a></td>
                            <td data-title='PASSENGER'><a class='button' href="/passengers/${train.getId()}"
                                                          data-type="modal-trigger">PASSENGERS</a></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <c:if test="${not empty model.success}">
                    <script>
                        swal({
                            title: 'Good job!',
                            text: 'Train is ready',
                            type: 'success'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty model.successdelete}">
                    <script>
                        swal({
                            title: 'Good job!',
                            text: 'Train is deleted',
                            type: 'success'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty model.trainexist}">
                    <script>
                        swal({
                            title: 'Oh boy!',
                            text: 'Train exist. Or maybe it\'s to late for train. Check train list and time. After 13.15 there is no new trains',
                            type: 'error'
                        });
                    </script>
                </c:if>
                <c:if test="${not empty model.systemError}">
                    <script>
                        swal({
                            title: 'OOOOOOPS...',
                            text: 'Something really bad happened. Try again!',
                            type: 'error'
                        });
                    </script>
                </c:if>

            </main>

            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach begin="1" end="${model.trainPages}" step="1" varStatus="loop">
                        <li class="page-item"><a class="page-link" href="/dashtrain/${loop.count}">${loop.count}</a>
                        </li>
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