package ru.itis.servlets;

import ru.itis.dao.*;
import ru.itis.dto.UserDto;
import ru.itis.services.UserTrainingService;
import ru.itis.services.UserTrainingServiceImpl;
import ru.itis.utils.DBWrapper;

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
public class UserTrainingServlet extends HttpServlet {
    private UserTrainingService userTrainingService;

    @Override
    public void init() throws ServletException {
        Connection conn = DBWrapper.getConnection();
        UserDao userDao = new UserDaoJdbcImpl(conn);
        TrainingDao trainingDao = new TrainingDaoJdbcImpl(conn);
        UserTrainingDao userTrainingDao = new UserTrainingDaoJdbcImpl(conn, trainingDao, userDao);

        userTrainingService = new UserTrainingServiceImpl(userTrainingDao, trainingDao, userDao);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        Integer doneEx = 0;
        if (req.getParameterValues("exercise") != null) {
            doneEx = req.getParameterValues("exercise").length;
        }
        String trainingName = req.getParameter("training-name");

        userTrainingService.addUserTraining(userDto, trainingName, doneEx);

        resp.sendRedirect(req.getContextPath() + "/trainings");
    }
}
