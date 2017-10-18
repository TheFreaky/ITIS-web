package ru.itis.dao;

import ru.itis.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private Connection connection;

    private final static String SQL_INSERT = "INSERT INTO users (email, password, sex, country, news_subscription) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM users";
    private final static String SQL_SELECT_BY_LOGIN = "SELECT * FROM users WHERE email = ?";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE users SET " +
            "(email, password, sex, country, news_subscription) = (?, ?, ?, ?, ?) WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM users WHERE id = ?";

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    public void save(User model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, model.getEmail());
            stmt.setString(2, model.getPassword());
            stmt.setString(3, model.getSex());
            stmt.setString(4, model.getCountry());
            stmt.setBoolean(5, model.getNewsSubscription());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                model.setId(id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User find(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            return User.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .sex(rs.getString("sex"))
                    .country(rs.getString("country"))
                    .newsSubscription(rs.getBoolean("news_subscription"))
                    .build();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(User model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_UPDATE);
            stmt.setString(1, model.getEmail());
            stmt.setString(2, model.getPassword());
            stmt.setString(3, model.getSex());
            stmt.setString(4, model.getCountry());
            stmt.setBoolean(5, model.getNewsSubscription());
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_DELETE);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(
                        User.builder()
                                .id(rs.getLong("id"))
                                .email(rs.getString("email"))
                                .password(rs.getString("password"))
                                .sex(rs.getString("sex"))
                                .country(rs.getString("country"))
                                .newsSubscription(rs.getBoolean("news_subscription"))
                                .build()
                );
            }

            return users;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_SELECT_BY_LOGIN);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return User.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .sex(rs.getString("sex"))
                    .country(rs.getString("country"))
                    .newsSubscription(rs.getBoolean("news_subscription"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}