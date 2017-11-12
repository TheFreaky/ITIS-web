package ru.itis.servlets;

import ru.itis.dao.*;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserProfileDto;
import ru.itis.dto.UserProfileForm;
import ru.itis.services.UserProfileService;
import ru.itis.services.UserProfileServiceImpl;
import ru.itis.utils.DbWrapper;
import ru.itis.utils.ViewPath;
import ru.itis.validators.UserProfileFormValidator;
import ru.itis.validators.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@MultipartConfig
public class UserProfileServlet extends HttpServlet {
    private UserProfileService userProfileService;
    private Validator<UserProfileForm> validator;

    @Override
    public void init() throws ServletException {
        Connection conn = DbWrapper.getConnection();
        UserDao userDao = new UserDaoJdbcImpl(conn);
        TrainingDao trainingDao = new TrainingDaoJdbcImpl(conn);
        UserTrainingDao userTrainingDao = new UserTrainingDaoJdbcImpl(conn, trainingDao, userDao);

        userProfileService = new UserProfileServiceImpl(userDao, userTrainingDao);
        validator = new UserProfileFormValidator();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfileForm form = UserProfileForm.builder()
                .name(req.getParameter("edit-name"))
                .gender(req.getParameter("edit-gender"))
                .weight(req.getParameter("edit-weight"))
                .height(req.getParameter("edit-gender"))
                .build();

        List<String> errors = validator.validate(form);

        if (errors.isEmpty()) {
            UserDto userDto = (UserDto) req.getSession().getAttribute("user");
            userDto = userProfileService.editUserProfile(form, userDto);
            req.getSession().setAttribute("user", userDto);

            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            req.setAttribute("profile", form);
            req.setAttribute("profileErrors", errors);
            req.getRequestDispatcher(ViewPath.PROFILE_PAGE).forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        UserProfileDto userProfile = userProfileService.getUserProfile(userDto.getId());
        req.setAttribute("userProfile", userProfile);

        RequestDispatcher dispatcher = req.getRequestDispatcher(ViewPath.PROFILE_PAGE);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
