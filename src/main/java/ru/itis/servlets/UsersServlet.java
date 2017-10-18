package ru.itis.servlets;

import ru.itis.dao.UserDao;
import ru.itis.dao.UserDaoJdbcImpl;
import ru.itis.entity.User;
import ru.itis.utils.DBWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 02.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UsersServlet extends HttpServlet {

    private UserDao userDao;
    private Comparator<User> userInverse;
    private Comparator<User> userByEmail;
    private Comparator<User> userByCountry;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoJdbcImpl(DBWrapper.getConnection());
        userInverse = Comparator.comparing(User::getId).reversed();
        userByEmail = Comparator.comparing(User::getEmail);
        userByCountry = Comparator.comparing(User::getEmail);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie sort = null;
        List<User> users = userDao.findAll();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("usersSort")) {
                sort = cookie;
                break;
            }
        }

        if (sort != null) {
            switch (sort.getValue()) {
                case "id_rev":
                    users.sort(userInverse);
                    break;
                case "email":
                    users.sort(userByEmail);
                    break;
                case "country":
                    users.sort(userByCountry);
                    break;
            }
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
    }


}
