package ru.itis.servlets;

import ru.itis.utils.ViewPathConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 06.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class WelcomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPathConfig.WELCOME_PAGE);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
