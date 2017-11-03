package ru.itis.servlets;

import ru.itis.dao.UserDao;
import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.entity.User;
import ru.itis.service.UserValidator;
import ru.itis.service.Validator;
import ru.itis.utils.DBWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 25.09.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class SignUpServlet extends HttpServlet {
    private UserDao userDao;
    private Validator<User> validator;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoJdbcImpl(DBWrapper.getConnection());
        validator = new UserValidator();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signup.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .login(req.getParameter("reg-login"))
                .password(req.getParameter("reg-pass"))
                .build();

        List<String> errors = validator.validate(user);

        if (errors.isEmpty()) {
            userDao.save(user);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("user", user);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        }
    }
}