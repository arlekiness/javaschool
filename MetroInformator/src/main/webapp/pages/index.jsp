<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>meTro-Systems - Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="static/images/sw.png" type="image/png">

    <!-- ==================================================
               javascript
================================================== -->
    <script src="static/js/jquery-3.2.1.js"></script>
    <script src="static/js/main.js"></script> <!-- Resource jQuery -->
    <script src="static/js/custom.js"></script>
    <script src="static/js/velocity.min.js"></script>

    <!-- css -->
    <link href="static/css/bootstrap2.min.css" rel="stylesheet" />
    <link href="static/css/style.css" rel="stylesheet" />
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
                        <a class="navbar-brand" href="/"><i class="icon-logo"></i></a>
                    </div>


                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="/">Home</a></li>
                            <li><a href="/tickets">Tickets</a></li>
                            <li><a href="/schedule">Schedule</a></li>
                        </ul>

                    </div>

                    <!--                гамбургерное меню    -->
                    <div class="ham-menu">
                        <input id="hamburger" class="hamburger" type="checkbox"/>
                        <label class="hamburger" for="hamburger">
                            <i></i>
                        </label>
                        <div class="drawer-list">
                            <ul>
                                <li class="active"><a href="/">Home</a></li>
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

    <div class="page container-fluid">
        <!-- ШАПКА -->
        <div id="banner" class="row">
            <!-- слайдер -->


            <div id="main-slider" class="col-md-12">
                <ul class="slides">
                    <li>
                        <div class="flex-caption">
                            <p class="hero-text">A first step</p>
                            <p class="hero-text">to your journey</p>
                            <!--                        <h3 class="dark-blue">VET<span>&amp;</span>PET</h3>-->
                            <p class="brand-bottom-text">
                                <span>Sign in or create new account first.</span></p>

                            <!-- КНОПКИ -->


                            <div class="register-signin-home pull-left main-nav1">
                                <ul>
                                    <li><span><a href="#0" class="cd-signin">SIGN IN</a></span></li>
                                    <li><span><a href="#0" class="cd-signup">REGISTER</a></span></li>
                                </ul>
                            </div>
                            <!-- POP UP -->

                            <div class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
                                <div class="cd-user-modal-container"> <!-- this is the container wrapper -->
                                    <ul class="cd-switcher">
                                        <li><a href="#0">SIGN IN</a></li>
                                        <li><a href="#0">REGISTER</a></li>
                                    </ul>

                                    <!-- LOGIN -->

                                    <div id="cd-login"> <!-- log in form -->
                                        <form class="cd-form" name="forma_1" action="/login" method="post">

                                            <p class="fieldset">
                                                <label class="image-replace cd-email" for="signin-email">E-mail address</label>
                                                <input class="full-width has-padding has-border" id="signin-email" type="text" maxlength="100" size="11" name="login" value="" placeholder="E-mail address">
                                                <span class="cd-error-message">Incorrect E-mail address</span>
                                            </p>
                                            <p class="fieldset">
                                                <label class="image-replace cd-password" for="signin-password">Password</label>
                                                <input class="full-width has-padding has-border" id="signin-password" type="password" maxlength="100" size="11" name="pas" value="" placeholder="Password">
                                                <a href="#0" class="hide-password">Show</a>
                                                <span class="cd-error-message">Incorrect Password</span>
                                            </p>
                                            <p class="fieldset" id="login-form">
                                                <input type="checkbox" id="remember-me" checked>
                                                <label for="remember-me">Remember me</label>
                                            </p>
                                            <p class="fieldset" onClick='enter()'>
                                                <input class="full-width" type="submit" value="SIGN IN">
                                            </p>
                                        </form>
                                        <p class="cd-form-bottom-message"><a href="#0">Forgot password?</a></p>
                                    </div>

                                    <!-- REGISTER -->

                                    <div id="cd-signup">
                                        <div class="create-account">
                                            <p>Creating new account</p>
                                            <p><span>Few words about reasons we need this info blah blah blah</span></p>
                                        </div>
                                        <form class="cd-form half-form">
                                            <p class="fieldset">
                                                <label class="image-replace cd-firstname" for="signup-firstname">First name</label>
                                                <input class="full-width has-padding has-border" id="signup-firstname" type="text" placeholder="First name">
                                            </p>

                                            <p class="fieldset">
                                                <label class="image-replace cd-lastname" for="signup-lastname">Last name</label>
                                                <input class="full-width has-padding has-border" id="signup-lastname" type="text" placeholder="Last name">
                                            </p>



                                            <!--
                                                                <p class="fieldset">
                                                                    <label class="image-replace cd-password" for="signup-date">Дата</label>
                                                                    <input class="full-width has-padding has-border" id="date" type="text"  placeholder="Date of birth" onClick="xCal(this)" onKeyUp="xCal()">
                                                                    <a href="#0" class="date-icon"><i class="fa fa-calendar"></i></a>
                                                                </p>
                                            -->


                                            <p class="fieldset">
                                                <label class="image-replace cd-email" for="signup-email">E-mail address</label>
                                                <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="E-mail address">
                                                <span class="cd-error-message">Проверьте правильность ввода e-mail</span>
                                            </p>

                                            <p class="fieldset">
                                                <label class="image-replace cd-password" for="signup-password">PASSWORD</label>
                                                <input class="full-width has-padding has-border" id="signup-password" type="text"  placeholder="Password">
                                                <a href="#0" class="hide-password">Show</a>
                                                <span class="cd-error-message">Неверный логин или пароль</span>
                                            </p>

                                            <p class="fieldset" id="login-form1">
                                                <input type="checkbox" id="accept-terms">
                                                <label for="accept-terms">I agree with <a href="#0"> all terms</a></label>
                                            </p>

                                            <p class="fieldset">
                                                <input class="full-width has-padding" type="submit" value="SIGN UP">
                                            </p>
                                        </form>

                                        <!-- <a href="#0" class="cd-close-form">Close</a> -->
                                    </div> <!-- cd-signup -->


                                    <div id="cd-reset-password"> <!-- reset password form -->
                                        <p class="cd-form-message">Enter your mail, we'll send your pass.</p>

                                        <form class="cd-form">
                                            <p class="fieldset">
                                                <label class="image-replace cd-email" for="reset-email">E-mail</label>
                                                <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="Ваш E-mail">
                                                <span class="cd-error-message">Check your E-mail!</span>
                                            </p>

                                            <p class="fieldset">
                                                <input class="full-width has-padding" type="submit" value="SEND MY PASS">
                                            </p>
                                        </form>

                                        <p class="cd-form-bottom-message"><a href="#0">Back</a></p>
                                    </div> <!-- cd-reset-password -->
                                    <a href="#0" class="cd-close-form">Close</a>
                                </div> <!-- cd-user-modal-container -->
                            </div> <!-- cd-user-modal -->

                        </div>
                    </li>
                </ul>
            </div>

            <!-- POP UP конец -->


        </div> </div>

    <div class="clearfix"></div>


</div>

</body>
</html>



