<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Pimp yourself</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link href="<c:url value="/resources/css/trainings.css"/>" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-static-top masthead navbar-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#top-nav">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Development</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse" id="top-nav">
            <ul class="nav navbar-nav">
                <li><a href="#"><i class="fa fa-suitcase"></i>Тренировки</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle profile-image" data-toggle="dropdown">
                        <img src="http://placehold.it/30x30" class="img-circle">Username<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Профиль</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Выйти</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>