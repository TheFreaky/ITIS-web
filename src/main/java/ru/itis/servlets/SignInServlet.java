package ru.itis.servlets;

import ru.itis.dao.impl.UserDaoJdbcImpl;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserSignInForm;
import ru.itis.services.UserService;
import ru.itis.services.impl.UserServiceImpl;
import ru.itis.utils.DbWrapper;
import ru.itis.utils.ViewPathConfig;
import ru.itis.validators.UserSignInFormValidator;
import ru.itis.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 07.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class SignInServlet extends HttpServlet {
    private UserService userService;
    private Validator<UserSignInForm> validator;

    @Override
    public void init() {
        userService = new UserServiceImpl(new UserDaoJdbcImpl(DbWrapper.getConnection()));
        validator = new UserSignInFormValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/welcome");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSignInForm form = UserSignInForm.builder()
                .password(req.getParameter("signin-password"))
                .login(req.getParameter("signin-username"))
                .build();
        List<String> errors = validator.validate(form);
        UserDto user = null;
        if (errors.isEmpty()) {
            user = userService.signIn(form);

            if (user == null) {
                errors.add("Invalid email or login!");
            }
        }
        if (errors.isEmpty()) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/trainings");
        } else {
            req.setAttribute("signinErrors", errors);
            req.getRequestDispatcher(ViewPathConfig.WELCOME_PAGE).forward(req, resp);
        }
    }
}
