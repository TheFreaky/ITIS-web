package ru.itis.dao;

import ru.itis.entity.Complexity;
import ru.itis.entity.Exercise;
import ru.itis.entity.Specialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 30.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class ExerciseDaoJdbcImpl implements ExerciseDao {

    private Connection connection;

    public ExerciseDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    private final static String SQL_INSERT = "INSERT INTO exercises (exercise_name, exercise_complexity, " +
            "exercise_type) VALUES (?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM exercises;";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM exercises WHERE exercise_id = ?;";
    private final static String SQL_SELECT_BY_NAME = "SELECT * FROM exercises WHERE exercise_name = ?;";
    private final static String SQL_UPDATE = "UPDATE exercises SET (exercise_name, exercise_complexity, exercise_type) " +
            "= (?, ?, ?) WHERE exercise_id = ?";
    private final static String SQL_DELETE = "DELETE FROM exercises WHERE exercise_id = ?";

    @Override
    public void save(Exercise model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, model.getName());
            if (model.getComplexity() != null) {
                stmt.setObject(2, model.getComplexity().toString());
            } else {
                stmt.setObject(2, model.getComplexity());
            }
            if (model.getType() != null) {
                stmt.setObject(3, model.getType().toString());
            } else {
                stmt.setObject(3, model.getType());
            }
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("exercise_id");
                model.setId(id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Exercise find(Integer id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Specialization specialization = null;
            if (rs.getString("exercise_type") != null) {
                specialization = Specialization.valueOf(rs.getString("exercise_type"));
            }

            Complexity complexity = null;
            if (rs.getString("exercise_complexity") != null) {
                complexity = Complexity.valueOf(rs.getString("exercise_complexity"));
            }
            return Exercise.builder()
                    .id(rs.getInt("exercise_id"))
                    .name(rs.getString("exercise_name"))
                    .complexity(complexity)
                    .type(specialization)
                    .build();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Exercise model) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE);
            stmt.setString(1, model.getName());
            if (model.getComplexity() != null) {
                stmt.setObject(2, model.getComplexity().toString());
            } else {
                stmt.setObject(2, model.getComplexity());
            }
            if (model.getType() != null) {
                stmt.setObject(3, model.getType().toString());
            } else {
                stmt.setObject(3, model.getType());
            }
            stmt.setInt(4, model.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Exercise> findAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);

            List<Exercise> exercises = new ArrayList<>();
            while (rs.next()) {
                Specialization specialization = null;
                if (rs.getString("exercise_type") != null) {
                    specialization = Specialization.valueOf(rs.getString("exercise_type"));
                }

                Complexity complexity = null;
                if (rs.getString("exercise_complexity") != null) {
                    complexity = Complexity.valueOf(rs.getString("exercise_complexity"));
                }
                exercises.add(
                        Exercise.builder()
                                .id(rs.getInt("exercise_id"))
                                .name(rs.getString("exercise_name"))
                                .complexity(complexity)
                                .type(specialization)
                                .build()
                );
            }

            return exercises;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Exercise findByName(String name) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_NAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Specialization specialization = null;
            if (rs.getString("exercise_type") != null) {
                specialization = Specialization.valueOf(rs.getString("exercise_type"));
            }

            Complexity complexity = null;
            if (rs.getString("exercise_complexity") != null) {
                complexity = Complexity.valueOf(rs.getString("exercise_complexity"));
            }
            return Exercise.builder()
                    .id(rs.getInt("exercise_id"))
                    .name(rs.getString("exercise_name"))
                    .complexity(complexity)
                    .type(specialization)
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
