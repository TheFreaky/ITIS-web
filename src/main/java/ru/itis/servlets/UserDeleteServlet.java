package ru.itis.servlets;

import ru.itis.dao.impl.UserDaoJdbcImpl;
import ru.itis.dto.UserDto;
import ru.itis.services.UserService;
import ru.itis.services.impl.UserServiceImpl;
import ru.itis.utils.DbWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 17.12.2017
 *
 * @author Kuznetsov Maxim
 */
public class UserDeleteServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl(new UserDaoJdbcImpl(DbWrapper.getConnection()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        userService.delete(userDto);

        resp.sendRedirect(req.getContextPath() + "/signout");
    }
}
