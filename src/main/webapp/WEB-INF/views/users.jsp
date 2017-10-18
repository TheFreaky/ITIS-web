<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 02.10.2017
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Email</th>
            <th>Sex</th>
            <th>Country</th>
            <th>News subscription</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.email}</td>
                <td>${user.sex}</td>
                <td>${user.country}</td>
                <td>${user.newsSubscription}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
