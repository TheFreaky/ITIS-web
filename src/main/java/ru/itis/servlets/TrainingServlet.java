package ru.itis.servlets;

import ru.itis.dao.*;
import ru.itis.dto.UserDto;
import ru.itis.services.TrainingService;
import ru.itis.services.TrainingServiceImpl;
import ru.itis.utils.DbWrapper;
import ru.itis.utils.ViewPathConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingServlet extends HttpServlet {
    private TrainingService trainingService;

    @Override
    public void init() throws ServletException {
        Connection conn = DbWrapper.getConnection();
        TrainingDao trainingDao = new TrainingDaoJdbcImpl(conn);
        UserDao userDao = new UserDaoJdbcImpl(conn);
        UserTrainingDao userTrainingDao = new UserTrainingDaoJdbcImpl(conn, trainingDao, userDao);
        trainingService = new TrainingServiceImpl(userDao, trainingDao, userTrainingDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        Integer doneEx = 0;
        if (req.getParameterValues("training-exercise") != null) {
            doneEx = req.getParameterValues("training-exercise").length;
        }
        String trainingName = req.getParameter("training-name");

        trainingService.addUserTraining(userDto, trainingName, doneEx);

        resp.sendRedirect(req.getContextPath() + "/trainings");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trainingName = req.getParameter("name");
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        //ToDo: check in jsp not null, if null u lvl too small
        req.setAttribute("training", trainingService.getTraining(trainingName, userDto));

        RequestDispatcher dispatcher = req.getRequestDispatcher(ViewPathConfig.TRAINING_PAGE);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
