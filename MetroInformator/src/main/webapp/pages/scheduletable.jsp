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
    <link href="static/css/style-shedule-table.css" rel="stylesheet" />
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
                            <li><a href="/tickets">Tickets</a></li>
                            <li class="active"><a href="/schedule">Schedule</a></li>
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
                                <li><a href="/">Home</a></li>
                                <li><a href="/tickets">Tickets</a></li>
                                <li class="active"><a href="shedule.html">Shedule</a></li>
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
        <p class="hero-text text-center">Check train shedule</p>

        <div class="hero-underline">
            <i class="fa fa-calendar date"></i><span class="hero-station">${model.scheduleList.get(0).getArrivalDate().toString().substring(0, 11)}</span>
            <i class="fa fa-circle station"></i><span class="hero-station">Ploshchad Alexandra Nevskogo-2</span>

        </div>

        <main>
            <table>
                <thead>
                <tr>
                    <th>
                        TRAIN
                    </th>
                    <th>
                        ARRIVAL
                    </th>
                    <th>
                        DIRECTION
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr class="table-first">
                    <td data-title='TRAIN'>T-001</td>
                    <td data-title='ARRIVAL'>10:00</td>
                    <td data-title='DIRECTION'><span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span></td>
                </tr>

                <tr class="table-first">
                    <td data-title='TRAIN'>T-001</td>
                    <td data-title='ARRIVAL'>10:00</td>
                    <td data-title='DIRECTION'><span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span></td>
                </tr>

                <tr class="table-first">
                    <td data-title='TRAIN'>T-001</td>
                    <td data-title='ARRIVAL'>10:00</td>
                    <td data-title='DIRECTION'><span><i class="fa fa-circle"></i> Ploshchad Alexandra Nevskogo-2</span></td>
                </tr>
                </tbody>
            </table>

        </main>



    </div>



    <!-- конец контента -->


    <div class="clearfix"></div>




</div>




</body>
</html>
