package ru.itis.servlets;

import ru.itis.dao.impl.UserDaoJdbcImpl;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserRegistrationForm;
import ru.itis.services.UserService;
import ru.itis.services.impl.UserServiceImpl;
import ru.itis.utils.DbWrapper;
import ru.itis.utils.ViewPathConfig;
import ru.itis.validators.UserRegistrationFormValidator;
import ru.itis.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 25.09.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class SignUpServlet extends HttpServlet {
    private Validator<UserRegistrationForm> validator;
    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl(new UserDaoJdbcImpl(DbWrapper.getConnection()));
        validator = new UserRegistrationFormValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/welcome");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistrationForm form = UserRegistrationForm.builder()
                .name(req.getParameter("signup-name"))
                .login(req.getParameter("signup-username"))
                .password(req.getParameter("signup-password"))
                .build();

        List<String> errors = validator.validate(form);

        if (errors.isEmpty()) {
            UserDto userDto = userService.register(form);

            req.getSession().setAttribute("user", userDto);
            resp.sendRedirect(req.getContextPath() + "/trainings");
        } else {
            req.setAttribute("user", form);
            req.setAttribute("signupErrors", errors);
            req.getRequestDispatcher(ViewPathConfig.WELCOME_PAGE).forward(req, resp);
        }
    }
}