package ru.itis.servlets;

import ru.itis.dao.TrainingDaoJdbcImpl;
import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.dto.TrainingDto;
import ru.itis.dto.UserDto;
import ru.itis.services.TrainingService;
import ru.itis.services.TrainingServiceImpl;
import ru.itis.utils.DBWrapper;

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
public class TrainingServlet extends HttpServlet {
    private TrainingService trainingService;

    @Override
    public void init() throws ServletException {
        Connection conn = DBWrapper.getConnection();
        trainingService = new TrainingServiceImpl(new UserDaoJdbcImpl(conn), new TrainingDaoJdbcImpl(conn));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path;
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        String trainingName = req.getParameter("name");
        String sort = req.getParameter("how");
        if (trainingName == null) {
            path = "/WEB-INF/views/trainings.jsp";
            req.setAttribute("trainings", getTrainings(sort, userDto));
        } else {
            path = "/WEB-INF/views/training.jsp";
            //ToDo: check in jsp not null, if null u lvl too small
            req.setAttribute("trainings", trainingService.getTraining(trainingName, userDto));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
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
