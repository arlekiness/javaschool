<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - 404</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="shortcut icon" href="/static/images/sw.png" type="image/png">

    <!-- ==================================================
               javascript
================================================== -->
    <script src="/static/js/jquery-3.2.1.js"></script>
    <script src="/static/js/jcanvas.js"></script>
    <script src="/static/js/main.js"></script> <!-- Resource jQuery -->
    <script src="/static/js/custom.js"></script>
    <script src="/static/js/velocity.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>

    <!-- css -->
    <link href="/static/css/bootstrap2.min.css" rel="stylesheet"/>
    <link href="/static/css/style-404.css" rel="stylesheet"/>
    <link href="/static/css/vypad-spiski-dlya-form.css" rel="stylesheet"/>
</head>

<body>
<div id="site-content" class="home-page">
    <div class="all-menu navbar-fixed-top">

        <!-- конец верхнего меню -->
        <!-- Основное меню -->
        <header>
            <div class="navbar">
                <div class="container eshe-container">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/"><em class="icon-logo"></em></a>
                    </div>


                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/">Home</a></li>
                            <li><a href="/tickets">Tickets</a></li>
                            <li><a href="/schedule">Schedule</a></li>
                        </ul>

                    </div>

                    <!--                гамбургерное меню    -->
                </div>
            </div>
        </header>
    </div>
    <!-- конец основного меню -->

    <div class="page container-fluid">
        <!-- ШАПКА -->
        <div id="banner" class="row">
            <!-- слайдер -->


            <div id="main-slider" class="col-md-12">
                <ul class="slides">
                    <li>
                        <div class="flex-caption">
                            <p class="hero-text">Oops</p>
                            <p class="brand-bottom-text">
                                <span>Something went wrong.</span></p>

                            <!-- КНОПКИ -->


                            <div class="register-signin-home pull-left main-nav1">
                                <ul>
                                    <li><span><a href="/" class="cd-signup">HOME</a></span></li>
                                </ul>
                            </div>

                        </div>
                    </li>
                </ul>
            </div>


            <!-- конец слайдера -->

        </div>
    </div>

    <div class="clearfix"></div>
</div>


</body>
</html>
