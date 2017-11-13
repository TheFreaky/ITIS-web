<%--@elvariable id="setting" type="ru.itis.dto.UserSettingForm"--%>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 13.11.2017
  Time: 1:15
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
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+vendors,,_jasny-bootstrap,,_css,,_jasny-bootstrap.css+vendors,,_select2,,_css,,_select2.min.css+vendors,,_select2,,_css,,_select2-bootstrap.css+vendors,,_bootstrapvalidator,,_css,,_bootstrapValidator.min.css+vendors,,_datetimepicker,,_css,,_bootstrap-datetimepicker.min.css,Mcc.iJBuO24LbF.css.pagespeed.cf.wwL0jY3rho.css"
          rel="stylesheet"/>
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

        .edit-user-content .panel {
            padding: 2em;
            background-color: #f2f1e6;
            border-radius: 3px;
            -webkit-box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
            box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
        }

        .edit-user-content .panel-body {
            margin-top: 1.5em;
        }

        .content .panel {
            padding: 3em;
            margin: 2em 3em;
            border-radius: 2em;
        }

        .content {
            height: 90.5vh;
        }

        .panel-heading {
            margin-bottom: 2em;
        }
    </style>

</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<section class="edit-user-content content">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">

                <div class="panel-heading col-md-12">
                    <h3 class="panel-title text-center">Account settings</h3>
                </div>

                <div class="panel-body">
                    <form id="adduser_form" method="POST" action="<c:url value="/setting"/>" enctype="multipart/form-data" class="form-horizontal bv-form"
                          novalidate="novalidate">
                        <h2 class="hidden">&nbsp;</h2>
                        <div class="form-group">
                            <label for="email" class="col-md-2 control-label">Email</label>
                            <div class="col-md-10">
                                <input id="email" name="setting-username" placeholder="E-mail" type="text"
                                       class="form-control required email" value="${setting.login}"
                                       data-bv-field="email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-md-2 control-label">Password</label>
                            <div class="col-md-10">
                                <input id="password" name="setting-password"" type="password" placeholder="Password"
                                       class="form-control required" data-bv-field="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password_confirm" class="col-md-2 control-label">Confirm Password</label>
                            <div class="col-md-10">
                                <input id="password_confirm" name="setting-password-repeat" type="password"
                                       placeholder="Confirm Password " class="form-control required"
                                       data-bv-field="password_confirm">
                            </div>
                        </div>
                        <input type="submit"
                               class="center-block finish-button button button button-3d button-primary button-rounded btn_3d"
                               value="Save">
                    </form>

                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>