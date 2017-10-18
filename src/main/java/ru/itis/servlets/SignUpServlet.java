package ru.itis.servlets;

import ru.itis.dao.UserDao;
import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.dto.UserRegistrationDto;
import ru.itis.entity.User;
import ru.itis.service.UserRegistrationValidator;
import ru.itis.service.Validator;
import ru.itis.utils.DBWrapper;
import ru.itis.utils.UserRegToUserConverter;
import ru.itis.utils.UserRegToUserConverterImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private Validator<UserRegistrationDto> validator;
    private UserRegToUserConverter converter;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoJdbcImpl(DBWrapper.getConnection());
        validator = new UserRegistrationValidator();
        converter = new UserRegToUserConverterImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signup.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistrationDto userReg = UserRegistrationDto.builder()
                .email(req.getParameter("reg-email"))
                .password(req.getParameter("reg-pass"))
                .passwordRepeat(req.getParameter("reg-pass-repeat"))
                .sex(req.getParameter("reg-sex"))
                .country(req.getParameter("reg-country"))
                .newsSubscription(req.getParameter("reg-news"))
                .build();

        List<String> errors = validator.validate(userReg);

        if (errors.isEmpty()) {
            User user = converter.convertToEntity(userReg);
            userDao.save(user);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("user", userReg);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        }
    }
}