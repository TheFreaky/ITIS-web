package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 03.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class CookieSetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("usersSort", request.getParameter("name"));
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
    }
}
