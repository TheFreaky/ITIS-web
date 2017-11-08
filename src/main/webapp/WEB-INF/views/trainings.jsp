<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 08.11.2017
  Time: 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Pimp yourself</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<c:url value="/resources/css/trainings.css"/>" rel="stylesheet" type='text/css'>
    <link href="<c:url value="/resources/css/nav.css"/>" rel="stylesheet" type='text/css'>
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet" type='text/css'>

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js" type="text/javascript"></script>

    <link href="http://coreplusdemo.lorvent.com/vendors/jquerylabel/css/jquery-labelauty.css" rel="stylesheet"
          type="text/css"/>
    <link href="http://coreplusdemo.lorvent.com/css/buttons_sass.css" rel="stylesheet" type="text/css"/>

    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+css,,_custom.css+vendors,,_simple-line-icons,,_css,,_simple-line-icons.css+css,,_custom_css,,_user_profile.css,Mcc.YDRojCQWPN.css.pagespeed.cf.f_4TVswlNK.css"
          rel="stylesheet"/>


    <style>
        .train {
            border-left: none;
            border-right: none;
        }

        .trainings-header {
            padding: 3em;
        }

    </style>
</head>
<body>


<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container">
    <div class="row trainings-header">
        <div class="col-sm-12 text-center">
            <h1>Trainings</h1>
        </div>
    </div>
    <div class="row properties">
        <div class="col-sm-3"></div>
        <form class="col-sm-6">
            <div class="list-group">

                <%--@elvariable id="trainings" type="java.util.List"--%>
                <c:forEach items="${trainings}" var="training">
                    <div class="train list-group-item row">

                        <div class="property row`">
                            <div class="col-sm-8">
                                <h4><b><a href="?name=${training.name}">${training.name}</a> </b></h4>
                                <h4><b>${training.complexity}</b></h4>
                                <h4><b>${training.type}</b></h4>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

        </form>
    </div>
</div>

</body>
</html>

