<%--@elvariable id="training" type="ru.itis.models.Training"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Pimp yourself</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="/resources/css/nav.css"/>" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<c:url value="/resources/css/trainings.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js" type="text/javascript"></script>

    <link href="http://coreplusdemo.lorvent.com/vendors/jquerylabel/css/jquery-labelauty.css" rel="stylesheet"
          type="text/css"/>
    <link href="http://coreplusdemo.lorvent.com/css/buttons_sass.css" rel="stylesheet" type="text/css"/>

    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+css,,_custom.css+vendors,,_simple-line-icons,,_css,,_simple-line-icons.css+css,,_custom_css,,_user_profile.css,Mcc.YDRojCQWPN.css.pagespeed.cf.f_4TVswlNK.css"
          rel="stylesheet"/>

    <style>
        .properties-header {
            background-color: #419ae8;
            padding: 1.3em;
        }

        .display-none {
            display: none;
        }
    </style>

    <script>
        $(document).ready(function () {
            $('.start-button').click(function (e) {
                e.preventDefault();
                $(this).addClass('display-none');
                $('.finish-button').removeClass('display-none');
                $('.checks').removeClass('display-none');
                $('.training-description').addClass('display-none');
                $('.points-description').addClass('display-none');
            });
        });
    </script>

</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container">
    <c:choose>
        <c:when test="${not empty training}">
        <div class="row training-name">
            <div class="col-sm-12 text-center">
                <h1>${training.name}</h1>
            </div>
        </div>
        <div class="row text-center points-description">
            <div class="col-sm-12">
                <h5>${training.xp} xp</h5>
            </div>
        </div>
        <div class="row text-center training-description">
            <div class="col-sm-12">
                <p>${training.description}</p>
            </div>
        </div>
        <div class="row properties">
            <div class="col-sm-3"></div>
            <form action="<c:url value="/usertraining"/>" method="POST" class="col-sm-6" role="form">
                <div class="list-group">
                    <div class="list-group-item properties-header row">
                        <div class="col-sm-11">
                            Exercises
                        </div>
                    </div>
                    <div class="list-group-item row">
                        <c:forEach items="${training.exercises}" var="exercise" varStatus="i">
                            <div class="property row">
                                <div class="col-sm-11">
                                        ${exercise.name}
                                </div>
                                <div class="col-sm-1 checks display-none">
                                    <input name="exercise" class="to-labelauty-icon check-icon labelauty" type="checkbox"
                                           id="labelauty-98774${i.index}" style="display: none;"><label
                                        for="labelauty-98774${i.index}"><span
                                        class="labelauty-unchecked-image"></span><span
                                        class="labelauty-checked-image"></span></label>
                                    <label for="labelauty-98774${i.index}"><span class="labelauty-unchecked-image"></span><span
                                            class="labelauty-checked-image"></span></label>
                                </div>

                            </div>
                        </c:forEach>

                    </div>
                </div>
                <div id="hidden-training-name" style="display: none;">
                    <input id="training-name" type="text" name="training-name" value="${training.name}"/>
                </div>

                <div class="buttons row col-sm-12">
                    <button class="center-block start-button button button-glow button-rounded button-primary-flat hvr-float-shadow">
                        Start
                    </button>
                    <input type="submit"
                           class="display-none center-block finish-button button button-glow button-rounded button-primary-flat hvr-float-shadow"
                           value="Finish">
                </div>
            </form>
        </div>
        </c:when>
        <c:otherwise>
            <p class="text-danger" style="">This training is not available for you.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>