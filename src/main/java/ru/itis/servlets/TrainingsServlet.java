package ru.itis.servlets;

import ru.itis.dao.TrainingDao;
import ru.itis.dao.UserDao;
import ru.itis.dao.UserTrainingDao;
import ru.itis.dao.impl.TrainingDaoJdbcImpl;
import ru.itis.dao.impl.UserDaoJdbcImpl;
import ru.itis.dao.impl.UserTrainingDaoJdbcImpl;
import ru.itis.dto.TrainingDto;
import ru.itis.dto.UserDto;
import ru.itis.services.TrainingService;
import ru.itis.services.impl.TrainingServiceImpl;
import ru.itis.utils.DbWrapper;
import ru.itis.utils.ViewPathConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * 06.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingsServlet extends HttpServlet {
    private TrainingService trainingService;

    @Override
    public void init() {
        Connection conn = DbWrapper.getConnection();
        UserDao userDao = new UserDaoJdbcImpl(conn);
        TrainingDao trainingDao = new TrainingDaoJdbcImpl(conn);
        UserTrainingDao userTrainingDao = new UserTrainingDaoJdbcImpl(conn, trainingDao, userDao);
        trainingService = new TrainingServiceImpl(userDao, trainingDao, userTrainingDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        String sort = req.getParameter("how");

        req.setAttribute("trainings", getTrainings(sort, userDto));

        RequestDispatcher dispatcher = req.getRequestDispatcher(ViewPathConfig.TRAININGS_PAGE);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }

    private List<TrainingDto> getTrainings(String sort, UserDto userDto) {
        if ("type".equals(sort)) {
            return trainingService.getTrainingsSortedByType(userDto);
        } else if ("complexity".equals(sort)) {
            return trainingService.getTrainingsSortedByComplexity(userDto);
        } else {
            return trainingService.getTrainings(userDto);
        }
    }

}
