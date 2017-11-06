package ru.itis.servlets;

import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserRegistrationDto;
import ru.itis.services.UserService;
import ru.itis.services.UserServiceImpl;
import ru.itis.utils.DBWrapper;
import ru.itis.validators.UserValidator;
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
    private Validator<UserRegistrationDto> validator;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserDaoJdbcImpl(DBWrapper.getConnection()));
        validator = new UserValidator();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistrationDto user = UserRegistrationDto.builder()
                .name(req.getParameter("signup-name"))
                .login(req.getParameter("signup-username"))
                .password(req.getParameter("signup-password"))
                .build();

        List<String> errors = validator.validate(user);

        if (errors.isEmpty()) {
            UserDto userDto = userService.register(user);

            req.getSession().setAttribute("user", userDto);
            resp.sendRedirect(req.getContextPath() + "/trainings");
        } else {
            req.setAttribute("user", user);
            req.setAttribute("signupErrors", errors);
            req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req, resp);
        }
    }
}