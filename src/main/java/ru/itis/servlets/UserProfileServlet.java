package ru.itis.servlets;

import ru.itis.dao.*;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserProfileDto;
import ru.itis.services.UserProfileService;
import ru.itis.services.UserProfileServiceImpl;
import ru.itis.utils.DBWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserProfileServlet extends HttpServlet {
    private UserProfileService userProfileService;

    @Override
    public void init() throws ServletException {
        Connection conn = DBWrapper.getConnection();
        UserDao userDao = new UserDaoJdbcImpl(conn);
        TrainingDao trainingDao = new TrainingDaoJdbcImpl(conn);
        UserTrainingDao userTrainingDao = new UserTrainingDaoJdbcImpl(conn, trainingDao, userDao);

        userProfileService = new UserProfileServiceImpl(userDao, userTrainingDao);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        UserProfileDto userProfile = userProfileService.getUserProfile(userDto.getId());
        req.setAttribute("userProfile", userProfile);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
