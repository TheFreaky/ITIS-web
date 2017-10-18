package ru.itis.servlets;

import ru.itis.dao.UserDao;
import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.entity.User;
import ru.itis.utils.DBWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 07.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class SignInServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoJdbcImpl(DBWrapper.getConnection());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signin.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("auth-password");
        String login = req.getParameter("auth-email").toLowerCase();

        User user = userDao.findByLogin(login);
        if (user != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/users");
        }  else {
            req.setAttribute("error", "Invalid login or password");
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
        }
    }
}
