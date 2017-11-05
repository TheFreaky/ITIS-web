package ru.itis.dao;

import ru.itis.entity.Complexity;
import ru.itis.entity.Exercise;
import ru.itis.entity.Specialization;
import ru.itis.entity.Training;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 01.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingDaoJdbcImpl implements TrainingDao {
    private Connection connection;

    public TrainingDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    private final static String SQL_INSERT = "INSERT INTO trainings (training_name, training_description, " +
            "training_xp, training_min_lvl) VALUES (?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id;";
    private final static String SQL_SELECT_BY_ID = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id WHERE t.training_id = ?;";
    private final static String SQL_SELECT_BY_NAME = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id WHERE training_name = ?;";
    private final static String SQL_UPDATE = "UPDATE trainings SET (training_name, training_description, " +
            "training_xp, training_min_lvl) = (?, ?, ?, ?) WHERE training_id = ?";
    private final static String SQL_DELETE = "DELETE FROM trainings WHERE training_id = ?";

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
                Integer id = resultSet.getInt("training_id");
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

            Set<Exercise> exercises = new HashSet<>();
            Training training = null;

            while (rs.next()) {
                if (training == null) {
                    training = Training.builder()
                            .id(rs.getInt("training_id"))
                            .name(rs.getString("training_name"))
                            .description(rs.getString("training_description"))
                            .xp(rs.getInt("training_xp"))
                            .minLvl(rs.getShort("training_min_lvl"))
                            .exercises(exercises)
                            .build();
                }

                Specialization specialization = null;
                if (rs.getString("exercise_type") != null) {
                    specialization = Specialization.valueOf(rs.getString("exercise_type"));
                }

                Complexity complexity = null;
                if (rs.getString("exercise_complexity") != null) {
                    complexity = Complexity.valueOf(rs.getString("exercise_complexity"));
                }
                exercises.add(Exercise.builder()
                        .id(rs.getInt("exercise_id"))
                        .name(rs.getString("exercise_name"))
                        .complexity(complexity)
                        .type(specialization)
                        .build());
            }
            return training;
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

            List<Training> trainings = new ArrayList<>();
            int lastId = -1;
            while (rs.next()) {
                if (lastId != rs.getInt("training_id")) {
                    lastId = rs.getInt("training_id");
                    trainings.add(
                            Training.builder()
                                    .id(lastId)
                                    .name(rs.getString("training_name"))
                                    .description(rs.getString("training_description"))
                                    .xp(rs.getInt("training_xp"))
                                    .minLvl(rs.getShort("training_min_lvl"))
                                    .exercises(new HashSet<>())
                                    .build());
                }

                if (rs.getInt("exercise_id") == 0) continue;

                Specialization specialization = null;
                if (rs.getString("exercise_type") != null) {
                    specialization = Specialization.valueOf(rs.getString("exercise_type"));
                }

                Complexity complexity = null;
                if (rs.getString("exercise_complexity") != null) {
                    complexity = Complexity.valueOf(rs.getString("exercise_complexity"));
                }
                trainings.get(trainings.size() - 1).getExercises().add(Exercise.builder()
                        .id(rs.getInt("exercise_id"))
                        .name(rs.getString("exercise_name"))
                        .complexity(complexity)
                        .type(specialization)
                        .build());
            }
            return trainings;
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

            Set<Exercise> exercises = new HashSet<>();
            Training training = null;

            while (rs.next()) {
                if (training == null) {
                    training = Training.builder()
                            .id(rs.getInt("training_id"))
                            .name(rs.getString("training_name"))
                            .description(rs.getString("training_description"))
                            .xp(rs.getInt("training_xp"))
                            .minLvl(rs.getShort("training_min_lvl"))
                            .exercises(exercises)
                            .build();
                }

                Specialization specialization = null;
                if (rs.getString("exercise_type") != null) {
                    specialization = Specialization.valueOf(rs.getString("exercise_type"));
                }

                Complexity complexity = null;
                if (rs.getString("exercise_complexity") != null) {
                    complexity = Complexity.valueOf(rs.getString("exercise_complexity"));
                }
                exercises.add(Exercise.builder()
                        .id(rs.getInt("exercise_id"))
                        .name(rs.getString("exercise_name"))
                        .complexity(complexity)
                        .type(specialization)
                        .build());
            }
            return training;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}