<%--@elvariable id="training" type="ru.itis.models.Training"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Pimp yourself</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="<c:url value="/resources/css/trainings.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js" type="text/javascript"></script>

    <link href="http://coreplusdemo.lorvent.com/vendors/jquerylabel/css/jquery-labelauty.css"
          rel="stylesheet"
          type="text/css"/>
    <link href="http://coreplusdemo.lorvent.com/css/buttons_sass.css"
          rel="stylesheet"
          type="text/css"/>
    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+css,,_custom.css+vendors,,_simple-line-icons,,_css,,_simple-line-icons.css+css,,_custom_css,,_user_profile.css,Mcc.YDRojCQWPN.css.pagespeed.cf.f_4TVswlNK.css"
          rel="stylesheet"/>
    <link href="<c:url value="/resources/css/style.css"/>"
          type="text/css"
          rel="stylesheet">


    <style>
        .content .panel {
            padding: 2em;
            background-color: #f2f1e6;
            border-radius: 3px;
            -webkit-box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
            box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
        }

        .content .panel {
            padding: 3em;
            margin: 2em 3em;
            border-radius: 2em;
        }

        .display-none {
            display: none;
        }

        .content {
            height: 90.5vh;
        }

        .train .property {
            padding: 1em 0 4em 0;
            margin: 1em;
            background-color: #fffef3;
            border-radius: 10px;
            -webkit-box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
            box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
        }

        .training-description {
            background-color: #fffef3;
            padding: 2em;
            margin-left: -3em;
            margin-right: -3em;
            -webkit-box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
            box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
        }

    </style>

    <script>
        $(document).ready(function () {
            $('.start-button').click(function (e) {
                e.preventDefault();
                $(this).addClass('display-none');
                $('.finish-button').removeClass('display-none');
                $('.train-checkbox').removeClass('display-none');
                $('.training-description').addClass('display-none');
                $('.points-description').addClass('display-none');
            });
        });
    </script>

</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container content">
    <c:choose>
        <c:when test="${not empty training}">
            <div class="row panel">

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
                    <form class="col-md-6 col-md-offset-3" action="<c:url value="/training"/>" method="POST">
                        <div class="list-group">
                            <c:forEach items="${training.exercises}" var="exercise" varStatus="i">
                                <div class="train row">

                                    <div class="property list-group-item row`">
                                        <div class="col-md-4 train-icons">
                                            <div class="icon col-md-4">
                                                <!— Упражнение Filled icon by Icons8 —>
                                                <img class="icon icons8-Упражнение-Filled" width="40" height="40"
                                                     src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAACtUlEQVRYR82YgTFEMRCGVwWoABWgAlSAClCBUwEqQAWoABWgAlTgVIAKmO8mudmJ7Es29x4yc3NzXrL5svnzb545+edt7p/zyVCAC2HhH84EMI7POI5rBdwIgdZCoM3wvSwifGK7EpEjESmBMv5SjQXwQEQevIA7InKWQJSSBByT3RodWeST8WzdC/jqhNPzAghomk3+vm0A3nkB2bK9Uso6nk+3TvV5D3LJDvMCEiTV2UMmMttGZpYMWHR5Hp49i8iq0e+zBbA2gZxGIDlQubYuIsABemj0uQYQ4VsrqIF55LQZHZk4ZirtAhyQLITxKcOLiGwCiGjna0iMPm+Zg4MMsI1oP1Z4vdWj0D9mfrIwAFNNeVkR/tRYw44AF826Kx7jVro69KlBgABDMrXts7SQFg3mNMepvWnwSHwR6zJbiwZTze2HzNVmjX7EQHNWdZnGatGg1hzbSnWp0Vuc9EJETsLh5JTDYJ30mW8znuxhG2QNS0ES1HROOb+jTWm7IsvjWTVYC3gaskYWgQSu1LC/rVk1yNZSS63GgWIR2obIklVd0jgTQK8Ppr6Hno4zhJbH1WQd++F0j/ryQSsrulLoNaA9sp9evX6UzBYN6onI0nXYBWprWjIBoFKUbtS9+mAabDEAWOLH63ZLJ8J63qJBHYvMkLnYrNsxgEVTzkH2pcEYO76R9bbVfQMCymWBupw2tMoJdrUhAAGw3l22Oi63WXAvICUKz+Nbv//WZqV4/0sDeQFnee2McxevWBrSC/hVm6qOfrouF8N5AWvKVNekkxchj3F7AePkpZehHCR+6a4orYDa93KvrBR7beDFreyqJK2DMeX7cKJ1DODI8J8C/gocq27dYjKX6rDXzMUtaQHMXVAHgWvJYO6KPxhcC2D639BB4VoA9UWA2wmXVLe3eWzjGwZvlBgi+5udAAAAAElFTkSuQmCC">
                                            </div>
                                            <div class="icon col-md-4">
                                                <!— Тяжелая атлетика Filled icon by Icons8 —>
                                                <img class="icon icons8-Тяжелая-атлетика-Filled" width="40" height="40"
                                                     src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAADZUlEQVRYR72YjXWNQRCG31sBKkAFpAJUQCpABaiAVEAqQAWoQFIBKkAFkgo4z7VvzmTPzn77/SRzzj3k3t2dZ2bnZ3d3+i+PJd2X9LD8fSLpqPz/uv55Xek/lXSyk/RGEj/W8krSu2uieynpbUPXMYB/JN1s/IgF9uhVc7JjDxpKzgA8k3Sj9aOkW1dNVtbPnHQOYEbPXAAxwMJWPJX0XNL3mfDsxidJHyWxjoXdA7Alp70YZMKjYABJxMJ3JP0qnzmMzOODsA4GOoy+JgsdAfikWNYaQyaTRADdbgz4PQCKYa0QOi+xnyUp6g4BZIFviQVfigGMYSvI6nvF+oM57gs78aN4kNDBi59LmWstdwAg8jdRxgIRJMar545yWoeN9jycgwNasrOSXqJEkLgdgI8mSkwEh42BMufs49PK2boXiRVOFH6OgIdle0Y8SAY7ESJg/L5e55hQMGBWyZkUO0ocV3uC8vOslCXiinJiifOiwZN6DdizJLM4dppWJsZ5H0r9BPhuyPxeBu8NifGVdRSKMgqQGEuMd6fJ4sjrOxFcWuxZPP6+ESMX4yJgPZgaBxhWRok1kTjk76xMkUgY8rMsUGew4xrdsc5eOKUuFa72ZGdscREwbhdx5riri3GrEPdOSOwO5eZSl5pbywCtPU1MEcP1VuFdyhfe82kpxt9I9msJIMqw0h4DgoDG+8AjeJkxQPs7OkhWkFPYJYAGoKxYgCRugHIyAUeft8RkG/Ieg5YC1l60QgP61OLvSbj6uyHIpYCtWOwpXOS9NR40TMzoHmDsHkOe86A1HpzjxdnZuxUgceUi3PPMYkcsnhhostN2BCbLCQc6SdYAmgZuAdg7EddKgWM8H2AnZS1g1uwnFZeaSesDNpU1gGvgDBRPRJtucQ+OreOwQX/ObnSGmWx/SzzYg6tP2YD4hAQwn/jE0Tp+XfLkXEA8wt2ifsvhaAV4N54KYLyktwxaDJjB0Wc5FIzc8GrvT7bAUQ9mcNxLgButbfUdZLIFjgBmcPtr4Ug9CWNqwEn9UwMyuMmtScDjA8HQEawH2IIjGcjEkXhrMUbAoQfSDLAFR80CbjTeWoDxHWYyg7PzYAvOj45r4NAX789DYdLyYB3IWz6mx8eBocenFiAedMGlbhE3W4mN9316ct1/59zVWOqbZsQAAAAASUVORK5CYII=">
                                            </div>
                                            <div class="icon col-md-4">
                                                <!— Гимнастика Filled icon by Icons8 —>
                                                <img class="icon icons8-Гимнастика-Filled" width="40" height="40"
                                                     src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAACUElEQVRYR+2Y4VEbQQyFHxUEKkjoACqAVBCoAKgAUgFQAaGDUAFJBUAFQAVABUAFYT7mblCEdk+3tgfPYP2xfd49vZWennS3pDm3pTnHp2kA3Je0LOlM0v20DzwpwF+SAIgBbnXeAF5K2jCgvkvi2tRs0gguAC4iOCkZFxH81BFck3QoaVPSk6Q/ko67731gPkxmAHURpOdG0rq5/mEA7yR9K/DnpyRaHOYB/i70Y9rgVUuvjqoYYAAs2V9JW92fj92gkK2Fo44m2fXhNJMFCEev057eFhLlvey+kg6Skq8DKT6QdJJ15NZRbERz0EoAS0VyK4nIYVT1j0EP8QJUYSWzt9ZJAMIprczwm5tj/zIOKmtSo1lrqytFeAzmVJpbARJJRLw3JCQaVHcrXGYPB61aK0Cvf1Ql1eltiAbwsKdMCLQVoHfMs4h/YPI0eOjW2EeE7a7YilFsARg5jrqOp0H/1GepcSoJuZoqwMgxXPPmZQgaEGXb431vf3eTlghm+efbIEMGgDw9qjxsAZhx4Nvgs+nZ/oBVHo4FyJBwbvJgO4tNj2+DdsDIUuT1fmMB2jcJ7C+RHMnZMYjtiOajW30jMRZgNj1+nvRtDe37Yg4QyVRTBG1k4BXy4oU2Gtd8IKIKj4R+dIp5i0WaAcEnjrx5nkYtzXMUjYykajTAodbJ/56n0VDgeVgcv8ZyMAOQKbufGVlfkhHPw14n//MxC4AZnQREioezAGgrvTZSea6GlTwLgBQSRUAKqczaOEVhUHAcKnzxOQuAGZ6m18w9wBcOUJYpqkhH4AAAAABJRU5ErkJggg==">
                                            </div>

                                        </div>
                                        <div class="col-md-6 train-description">
                                            <h5><b>${training.complexity}</b></h5>
                                        </div>
                                        <div class="col-md-2 train-checkbox display-none">
                                            <input name="training-exercise" class="to-labelauty-icon check-icon labelauty"
                                                   type="checkbox"
                                                   id="labelauty-${i.index}" style=""><label
                                                for="labelauty-${i.index}"><span
                                                class="labelauty-unchecked-image"></span><span
                                                class="labelauty-checked-image"></span></label>
                                            <label for="labelauty-${i.index}"><span
                                                    class="labelauty-unchecked-image"></span><span
                                                    class="labelauty-checked-image"></span></label>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
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
            </div>
        </c:when>
        <c:otherwise>
            <p class="text-danger">This training is not available for you.</p>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>