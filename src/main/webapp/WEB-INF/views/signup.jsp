<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="reg-block">

    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="block-title">Sign up</h1>
        </div>
    </div>

    <div class="row auth-form">
        <div class="col-lg-8 col-lg-offset-2">
            <c:forEach items="${errors}" var="error">
                <div class="text-danger">* ${error}</div>
            </c:forEach>

            <form class="col-xs-12" method="post" action="">
                <div class="form-group">
                    <label>E-mail</label>
                    <input type="text" class="required-field form-control" placeholder="example@mail.ru"
                           name="reg-email"
                    <c:if test="${not empty user.email}">
                           value="${user.email}"
                    </c:if>
                    >
                </div>

                <div class="form-group">
                    <label for="reg-pass">Password</label>
                    <input type="password" class="required-field form-control" name="reg-pass" id="reg-pass">
                </div>

                <div class="form-group">
                    <label for="reg-pass-repeat">Confirm password</label>
                    <input type="password" class="required-field form-control" name="reg-pass-repeat"
                           id="reg-pass-repeat">
                </div>

                <div class="form-group">
                    <div class="radio-selects">
                        <label>Sex:</label>
                        <div>
                            <input type="radio" name="reg-sex" value="male" id="reg-sex-male"
                            <c:if test="${user.sex.equals('male')}">
                                   checked
                            </c:if>
                            >
                            <label for="reg-sex-male">Male</label>
                        </div>
                        <div>
                            <input type="radio" name="reg-sex" value="female" id="reg-sex-female"
                            <c:if test="${user.sex.equals('female')}">
                                   checked
                            </c:if>
                            >
                            <label for="reg-sex-female">Female</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="reg-country">Choose country</label>
                    <select name="reg-country" class="input-medium form-control" id="reg-country">
                        <option value="null">--------------</option>
                        <option value="CN"
                                <c:if test="${user.country.equals('CN')}">selected="selected" </c:if> >China
                        </option>
                        <option value="PL"
                                <c:if test="${user.country.equals('PL')}">selected="selected" </c:if> >Poland
                        </option>
                        <option value="RU"
                                <c:if test="${user.country.equals('RU')}">selected="selected" </c:if> >Russian
                            Federation
                        </option>
                        <option value="TR"
                                <c:if test="${user.country.equals('TR')}">selected="selected" </c:if> >Turkey
                        </option>
                        <option value="UA"
                                <c:if test="${user.country.equals('UA')}">selected="selected" </c:if> >Ukraine
                        </option>
                        <option value="AE"
                                <c:if test="${user.country.equals('AE')}">selected="selected" </c:if> >United Arab
                            Emirates
                        </option>
                        <option value="UK"
                                <c:if test="${user.country.equals('UK')}">selected="selected" </c:if> >United Kingdom
                        </option>
                        <option value="US"
                                <c:if test="${user.country.equals('US')}">selected="selected" </c:if> >United States
                        </option>
                    </select>
                </div>

                <div class="checkbox">
                    <label><input type="checkbox" name="reg-news" value="true"
                    <c:if test="${not empty user.newsSubscription and user.newsSubscription}">
                                  checked
                    </c:if>
                    >Subscribe on news</label>
                </div>

                <div class="form-group">
                    <button type="submit" class="reg-button btn btn-success btn-lg">Next</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>