<%--@elvariable id="userProfile" type="ru.itis.dto.UserProfileDto"--%>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 08.11.2017
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js" type="text/javascript"></script>

    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+css,,_custom.css+vendors,,_simple-line-icons,,_css,,_simple-line-icons.css+css,,_custom_css,,_user_profile.css,Mcc.YDRojCQWPN.css.pagespeed.cf.f_4TVswlNK.css"
          rel="stylesheet"/>

    <style>
        .progress span {
            position: absolute;
            display: block;
            width: 100%;
            color: black;
        }

        .progress {
            height: 1.5em;
        }

        header {
            margin-bottom: 1em;
        }

    </style>

</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>


<section class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <div class="col-md-4">
                        <div class="form-group">
                            <div class="text-center mbl">
                                <img src="data:image/webp;base64,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"
                                     alt="img" class="img-circle img-bor"/>
                            </div>
                        </div>
                        <div class="profile_user text-center">
                            <h3 class="user_name_max">${userProfile.name}</h3>
                            <p>${userProfile.login}</p>
                            <h4>Level ${userProfile.lvl}</h4>
                            <div class="progress progress-xs">
                                <div class="progress-bar" role="progressbar" aria-valuenow="${userProfile.progress}"
                                     aria-valuemin="0"
                                     aria-valuemax="100" style="width: ${userProfile.progress}%">
                                    <span class="show">${userProfile.xp}/${userProfile.xpToLvlUp}</span>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-widget panel-default">
                            <div class="panel-heading">
                                <span class="panel-title">Skills</span>
                            </div>
                            <div class="panel-body profile_status">
                                <div>
                                    <p>
                                        <strong>Flexibility</strong>
                                        <small class="pull-right text-muted">
                                            Level ${userProfile.flexibilityLvl}
                                        </small>
                                    </p>
                                    <div class="progress progress-xs">
                                        <div class="progress-bar progress-bar-success" role="progressbar"
                                             aria-valuenow="${userProfile.flexibilityProgress}" aria-valuemin="0"
                                             aria-valuemax="100"
                                             style="width: ${userProfile.flexibilityProgress}%">
                                            <span class="show">${userProfile.flexibilityProgress}/100</span>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <p>
                                        <strong>Stamina</strong>
                                        <small class="pull-right text-muted">
                                            Level ${userProfile.staminaLvl}
                                        </small>
                                    </p>
                                    <div class="progress progress-xs">
                                        <div class="progress-bar progress-bar-warning" role="progressbar"
                                             aria-valuenow="${userProfile.staminaProgress}" aria-valuemin="0"
                                             aria-valuemax="100"
                                             style="width: ${userProfile.staminaProgress}%">
                                            <span class="show">${userProfile.staminaProgress}/100</span>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <p>
                                        <strong>Strength</strong>
                                        <small class="pull-right text-muted">
                                            Level ${userProfile.strengthLvl}
                                        </small>
                                    </p>
                                    <div class="progress progress-xs">
                                        <div class="progress-bar progress-bar-info" role="progressbar"
                                             aria-valuenow="${userProfile.strengthProgress}" aria-valuemin="0"
                                             aria-valuemax="100"
                                             style="width: ${userProfile.strengthProgress}%">
                                            <span class="show">${userProfile.strengthProgress}/100</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs nav-custom">
                                    <li class="active">
                                        <a href="#tab-activity" data-toggle="tab">
                                            <strong>Last trainings</strong>
                                        </a>
                                    </li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content nopadding noborder">
                                    <div id="tab-activity" class="tab-pane fade in active">
                                        <div class="table-responsive">
                                            <table class="table table-responsive">
                                                <tbody>
                                                <c:forEach items="${userProfile.userTrainings}" var="userTraining">
                                                    <tr>
                                                        <td>
                                                                ${userTraining.training.name}
                                                        </td>
                                                        <td>
                                                                ${userTraining.date}
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!-- tab-content -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>