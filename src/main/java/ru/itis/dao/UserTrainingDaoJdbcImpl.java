package ru.itis.dao;

import ru.itis.models.User;
import ru.itis.models.UserTraining;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 04.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserTrainingDaoJdbcImpl implements UserTrainingDao {
    private Connection connection;
    private TrainingDao trainingDao;
    private UserDao userDao;

    private final static String SQL_INSERT = "INSERT INTO users_trainings (user_id, training_id, date) " +
            "VALUES (?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM users_trainings";
    private final static String SQL_SELECT_BY_USER = "SELECT * FROM users_trainings WHERE user_id = ? ORDER BY date DESC LIMIT 10";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM users_trainings WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE users_trainings SET (user_id, training_id, date) = " +
            "(?, ?, ?) WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM users_trainings WHERE id = ?";

    public UserTrainingDaoJdbcImpl(Connection connection, TrainingDao trainingDao, UserDao userDao) {
        this.connection = connection;
        this.trainingDao = trainingDao;
        this.userDao = userDao;
    }

    @Override
    public void save(UserTraining model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            if (model.getUser() != null) {
                stmt.setLong(1, model.getUser().getId());
            } else {
                stmt.setObject(1, null);
            }
            if (model.getTraining() != null) {
                stmt.setInt(2, model.getTraining().getId());
            } else {
                stmt.setObject(2, null);
            }
            stmt.setDate(3, Date.valueOf(model.getDate()));
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                model.setId(id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public UserTraining find(Integer id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return UserTraining.builder()
                    .id(rs.getInt("id"))
                    .date(rs.getDate("date").toLocalDate())
                    .user(userDao.find(rs.getLong("user_id")))
                    .training(trainingDao.find(rs.getInt("training_id")))
                    .build();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(UserTraining model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_UPDATE);
            stmt.setLong(1, model.getUser().getId());
            stmt.setInt(2, model.getTraining().getId());
            stmt.setDate(3, Date.valueOf(model.getDate()));
            stmt.setInt(4, model.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) {
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
    public List<UserTraining> findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);

            List<UserTraining> userTrainings = new ArrayList<>();
            while (rs.next()) {
                userTrainings.add(
                        UserTraining.builder()
                                .id(rs.getInt("id"))
                                .date(rs.getDate("date").toLocalDate())
                                .user(userDao.find(rs.getLong("user_id")))
                                .training(trainingDao.find(rs.getInt("training_id")))
                                .build()
                );
            }

            return userTrainings;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<UserTraining> findByUserId(Long userId) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_USER);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            List<UserTraining> userTrainings = new ArrayList<>();
            User user = null;
            while (rs.next()) {
                if (user == null) {
                    user = userDao.find(rs.getLong("user_id"));
                }
                userTrainings.add(
                        UserTraining.builder()
                                .id(rs.getInt("id"))
                                .date(rs.getDate("date").toLocalDate())
                                .user(user)
                                .training(trainingDao.find(rs.getInt("training_id")))
                                .build()
                );
            }

            return userTrainings;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
