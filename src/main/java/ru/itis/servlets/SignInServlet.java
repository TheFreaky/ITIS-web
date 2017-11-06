package ru.itis.servlets;

import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserSignInDto;
import ru.itis.services.UserService;
import ru.itis.services.UserServiceImpl;
import ru.itis.utils.DBWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 07.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class SignInServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserDaoJdbcImpl(DBWrapper.getConnection()));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("signin-password");
        String email = req.getParameter("signin-username").toLowerCase();

        UserDto user = userService.signIn(
                UserSignInDto.builder()
                        .password(password)
                        .login(email)
                        .build());
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/trainings");
        } else {
            req.setAttribute("signinErrors", "Invalid email or password");
            req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req, resp);
        }
    }
}
