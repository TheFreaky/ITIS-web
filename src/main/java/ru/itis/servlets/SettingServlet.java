package ru.itis.servlets;

import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserSettingForm;
import ru.itis.services.UserService;
import ru.itis.services.UserServiceImpl;
import ru.itis.utils.DbWrapper;
import ru.itis.utils.ViewPathConfig;
import ru.itis.validators.UserSettingFormValidator;
import ru.itis.validators.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class SettingServlet extends HttpServlet {
    private Validator<UserSettingForm> validator;
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl(new UserDaoJdbcImpl(DbWrapper.getConnection()));
        validator = new UserSettingFormValidator();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSettingForm form = UserSettingForm.builder()
                .login(req.getParameter("setting-username"))
                .password(req.getParameter("setting-password"))
                .passwordRepeat(req.getParameter("setting-password-repeat"))
                .build();


        List<String> errors = validator.validate(form);

        if (errors.isEmpty()) {
            UserDto userDto = (UserDto) req.getSession().getAttribute("user");
            service.editUserData(form, userDto);

            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            req.setAttribute("setting", form);
            req.setAttribute("settingErrors", errors);
            req.getRequestDispatcher(ViewPathConfig.SETTING_PAGE).forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(ViewPathConfig.SETTING_PAGE);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
