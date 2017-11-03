package ru.itis.dao;

import ru.itis.entity.Training;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 01.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingDaoImpl implements TrainingDao {
    private Connection connection;

    public TrainingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private final static String SQL_INSERT = "INSERT INTO trainings (name, description, " +
            "xp, min_lvl) VALUES (?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM trainings;";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM trainings WHERE trainings.id = ?;";
    private final static String SQL_SELECT_BY_NAME = "SELECT * FROM trainings WHERE trainings.name = ?;";
    private final static String SQL_UPDATE = "UPDATE trainings SET (name, description, " +
            "xp, min_lvl) = (?, ?, ?, ?) WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM trainings WHERE trainings.id = ?";

    @Override
    public void save(Training model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, model.getName());
            stmt.setObject(2, model.getDescription());
            stmt.setObject(3, model.getXp());
            stmt.setObject(4, model.getMinLvl());
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
    public Training find(Integer id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return Training.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .xp(rs.getInt("xp"))
                    .minLvl(rs.getShort("min_lvl"))
                    .build();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Training model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_UPDATE);
            stmt.setString(1, model.getName());
            stmt.setObject(2, model.getDescription());
            stmt.setObject(3, model.getXp());
            stmt.setObject(4, model.getMinLvl());
            stmt.setLong(5, model.getId());
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
    public List<Training> findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);

            List<Training> exercises = new ArrayList<>();
            while (rs.next()) {
                exercises.add(
                        Training.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .description(rs.getString("description"))
                                .xp(rs.getInt("xp"))
                                .minLvl(rs.getShort("min_lvl"))
                                .build()
                );
            }

            return exercises;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Training findByName(String name) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_SELECT_BY_NAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return Training.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .xp(rs.getInt("xp"))
                    .minLvl(rs.getShort("min_lvl"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
