package ru.itis.dao;

import ru.itis.entity.Specialization;
import ru.itis.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private Connection connection;

    private final static String SQL_INSERT = "INSERT INTO users (user_login, user_password, user_name, user_weight, " +
            "user_height, user_specialization, user_xp, user_strength, user_stamina, user_flexibility, user_gender) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM users";
    private final static String SQL_SELECT_BY_LOGIN = "SELECT * FROM users WHERE user_login = ?";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private final static String SQL_UPDATE = "UPDATE users SET (user_login, user_password, user_name, user_weight, " +
            "user_height, user_specialization, user_xp, user_strength, user_stamina, user_flexibility, user_gender) = " +
            "(?, ?, ?, ?, ?, ?::specialization, ?, ?, ?, ?, ?) WHERE user_id = ?";
    private final static String SQL_DELETE = "DELETE FROM users WHERE user_id = ?";

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    public void save(User model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, model.getLogin());
            stmt.setString(2, model.getPassword());
            stmt.setString(3, model.getName());
            stmt.setObject(4, model.getWeight());
            stmt.setObject(5, model.getHeight());
            if (model.getSpecialization() != null) {
                stmt.setString(6, model.getSpecialization().toString());
            } else {
                stmt.setObject(6, model.getSpecialization());
            }
            stmt.setObject(7, model.getXp());
            stmt.setObject(8, model.getStrength());
            stmt.setObject(9, model.getStamina());
            stmt.setObject(10, model.getFlexibility());
            stmt.setObject(11, model.getGender());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                model.setId(id);
            }
        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) { //integrity constraint violation
                throw new IllegalArgumentException("Xp can't be less than zero.", e);
            }
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User find(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Specialization specialization = null;
            if (rs.getString("user_specialization") != null) {
                specialization = Specialization.valueOf(rs.getString("user_specialization"));
            }

            return User.builder()
                    .id(rs.getLong("user_id"))
                    .login(rs.getString("user_login"))
                    .password(rs.getString("user_password"))
                    .name(rs.getString("user_name"))
                    .weight(rs.getFloat("user_weight"))
                    .height(rs.getShort("user_height"))
                    .specialization(specialization)
                    .xp(rs.getInt("user_xp"))
                    .strength(rs.getShort("user_strength"))
                    .stamina(rs.getShort("user_stamina"))
                    .flexibility(rs.getShort("user_flexibility"))
                    .gender(rs.getBoolean("user_gender"))
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
            stmt.setString(1, model.getLogin());
            stmt.setString(2, model.getPassword());
            stmt.setString(3, model.getName());
            stmt.setObject(4, model.getWeight());
            stmt.setObject(5, model.getHeight());
            if (model.getSpecialization() != null) {
                stmt.setString(6, model.getSpecialization().toString());
            } else {
                stmt.setObject(6, model.getSpecialization());
            }
            stmt.setObject(7, model.getXp());
            stmt.setObject(8, model.getStrength());
            stmt.setObject(9, model.getStamina());
            stmt.setObject(10, model.getFlexibility());
            stmt.setObject(11, model.getGender());
            stmt.setLong(12, model.getId());
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
                Specialization specialization = null;
                if (rs.getString("user_specialization") != null) {
                    specialization = Specialization.valueOf(rs.getString("user_specialization"));
                }
                users.add(
                        User.builder()
                                .id(rs.getLong("user_id"))
                                .login(rs.getString("user_login"))
                                .password(rs.getString("user_password"))
                                .name(rs.getString("user_name"))
                                .weight(rs.getFloat("user_weight"))
                                .height(rs.getShort("user_height"))
                                .specialization(specialization)
                                .xp(rs.getInt("user_xp"))
                                .strength(rs.getShort("user_strength"))
                                .stamina(rs.getShort("user_stamina"))
                                .flexibility(rs.getShort("user_flexibility"))
                                .gender(rs.getBoolean("user_gender"))
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

            if (!rs.next()) {
                return null;
            }
            Specialization specialization = null;
            if (rs.getString("user_specialization") != null) {
                specialization = Specialization.valueOf(rs.getString("user_specialization"));
            }
            return User.builder()
                    .id(rs.getLong("user_id"))
                    .login(rs.getString("user_login"))
                    .password(rs.getString("user_password"))
                    .name(rs.getString("user_name"))
                    .weight(rs.getFloat("user_weight"))
                    .height(rs.getShort("user_height"))
                    .specialization(specialization)
                    .xp(rs.getInt("user_xp"))
                    .strength(rs.getShort("user_strength"))
                    .stamina(rs.getShort("user_stamina"))
                    .flexibility(rs.getShort("user_flexibility"))
                    .gender(rs.getBoolean("user_gender"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}