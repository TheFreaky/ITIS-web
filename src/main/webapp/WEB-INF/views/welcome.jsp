<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Pimp yourself</title>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>

    <link href="<c:url value="/resources/css/scrolling-nav.css"/>" rel="stylesheet">

    <script>
        window.onload = function () {

            <c:if test="${not empty signinErrors}">
            $('#myModal').modal('show');
            </c:if>
            <c:if test="${not empty signupErrors}">
            $('#myModal').modal('show');
            $('li > a[href="#signup"]').tab("show");
            </c:if>
        }
    </script>

</head>

<body id="page-top">

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#">Pimp yourself</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <button type="button" class="btn btn-default navbar-btn">
                        <a class="nav-link js-scroll-trigger" href="#signin" data-toggle="modal"
                           data-target=".bs-modal-sm">Войти</a>
                    </button>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="row">
                <div class="form-body">
                    <ul class="nav nav-tabs final-login" role="tablist">
                        <li class="nav-item"><a class="flex-sm-fill text-sm-center nav-link active" data-toggle="tab"
                                                href="#signin">Войти</a>
                        </li>
                        <li class="nav-item"><a class="flex-sm-fill text-sm-center nav-link " data-toggle="tab"
                                                href="#signup">Регистрация</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="signin" class="tab-pane active show fade">
                            <div class="social-login">
                                <ul>
                                    <li><a href=""><i class="fa fa-facebook"></i> Facebook</a></li>
                                    <li><a href=""><i class="fa fa-google-plus"></i> Google+</a></li>
                                    <li><a href=""><i class="fa fa-twitter"></i> Twitter</a></li>
                                </ul>
                            </div>
                            <div class="inner-form">
                                <%--@elvariable id="signinErrors" type="java.util.List"--%>
                                <c:forEach items="${signinErrors}" var="error">
                                    <div class="text-danger">* ${error}</div>
                                </c:forEach>
                                <form class="sa-innate-form" method="post" action="<c:url value="/signin"/>">
                                    <label for="signin-username-field">Email:</label>
                                    <input id="signin-username-field" type="text" name="signin-username">
                                    <label for="signin-password-field">Пароль:</label>
                                    <input id="signin-password-field" type="password" name="signin-password">
                                    <button type="submit">Войти</button>
                                    <a href="">Забыли пароль?</a>
                                </form>
                            </div>
                        </div>
                        <div id="signup" class="tab-pane fade">
                            <div class="social-login">
                                <ul>
                                    <li><a href=""><i class="fa fa-facebook"></i> Facebook</a></li>
                                    <li><a href=""><i class="fa fa-google-plus"></i> Google+</a></li>
                                    <li><a href=""><i class="fa fa-twitter"></i> Twitter</a></li>
                                </ul>
                            </div>
                            <div class="inner-form">
                                <%--@elvariable id="signupErrors" type="java.util.List"--%>
                                <c:forEach items="${signupErrors}" var="error">
                                    <div class="text-danger">* ${error}</div>
                                </c:forEach>
                                <form class="sa-innate-form" method="post" action="<c:url value="/signup"/>">
                                    <label for="signup-name">Имя:</label>
                                    <input id="signup-name" type="text" name="signup-name"
                                    <%--@elvariable id="user" type="ru.itis.dto.UserRegistrationDto"--%>
                                    <c:if test="${not empty user}">
                                           value="${user.name}"
                                    </c:if>
                                    >
                                    <label for="signup-username">Email:</label>
                                    <input id="signup-username" type="text" name="signup-username">
                                    <label for="signup-password">Пароль:</label>
                                    <input id="signup-password" type="password" name="signup-password">
                                    <button type="submit">Зарегистрироваться</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<header class="bg-primary text-white">
    <div class="container text-center">
        <h1>Welcome to Pimp Yourself</h1>
        <p class="lead">Short description</p>
    </div>
</header>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>About this project</h2>
                <p class="lead">Description</p>
            </div>
        </div>
    </div>
</section>

<section id="services" class="bg-light">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>We offer</h2>
                <p class="lead">...</p>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Pimp Yourself 2017</p>
    </div>
    <!-- /.container -->
</footer>
</body>

</html>
