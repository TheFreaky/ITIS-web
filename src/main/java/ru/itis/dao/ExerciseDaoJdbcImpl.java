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

    private final static String SQL_INSERT = "INSERT INTO exercises (name, complexity, description, " +
            "instruction, fails, resource, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM exercises;";
    private final static String SQL_SELECT_BY_ID = "SELECT * FROM exercises WHERE exercises.id = ?;";
    private final static String SQL_SELECT_BY_NAME = "SELECT * FROM exercises WHERE exercises.name = ?;";
    private final static String SQL_UPDATE = "UPDATE exercises SET (name, complexity, description, " +
            "instruction, fails, resource, type) = (?, ?, ?, ?, ?, ?, ?) WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM exercises WHERE id = ?";

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
            stmt.setObject(3, model.getDescription());
            stmt.setObject(4, model.getInstruction());
            stmt.setObject(5, model.getFails());
            stmt.setObject(6, model.getResource());
            if (model.getType() != null) {
                stmt.setObject(7, model.getType().toString());
            } else {
                stmt.setObject(7, model.getType());
            }
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                model.setId(id);
            }
        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) { //integrity constraint violation
                throw new IllegalArgumentException("Xp can't be less than zero.");
            }
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
            if (rs.getString("type") != null) {
                specialization = Specialization.valueOf(rs.getString("type"));
            }

            Complexity complexity = null;
            if (rs.getString("complexity") != null) {
                complexity = Complexity.valueOf(rs.getString("complexity"));
            }
            return Exercise.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .complexity(complexity)
                    .description(rs.getString("description"))
                    .instruction(rs.getString("instruction"))
                    .fails(rs.getString("fails"))
                    .type(specialization)
                    .resource(rs.getString("resource"))
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
            stmt.setObject(3, model.getDescription());
            stmt.setObject(4, model.getInstruction());
            stmt.setObject(5, model.getFails());
            stmt.setObject(6, model.getResource());
            if (model.getType() != null) {
                stmt.setObject(7, model.getType().toString());
            } else {
                stmt.setObject(7, model.getType());
            }
            stmt.setInt(8, model.getId());
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
                if (rs.getString("type") != null) {
                    specialization = Specialization.valueOf(rs.getString("type"));
                }

                Complexity complexity = null;
                if (rs.getString("complexity") != null) {
                    complexity = Complexity.valueOf(rs.getString("complexity"));
                }
                exercises.add(
                        Exercise.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .complexity(complexity)
                                .description(rs.getString("description"))
                                .instruction(rs.getString("instruction"))
                                .fails(rs.getString("fails"))
                                .type(specialization)
                                .resource(rs.getString("resource"))
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
            if (rs.getString("type") != null) {
                specialization = Specialization.valueOf(rs.getString("type"));
            }

            Complexity complexity = null;
            if (rs.getString("complexity") != null) {
                complexity = Complexity.valueOf(rs.getString("complexity"));
            }
            return Exercise.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .complexity(complexity)
                    .description(rs.getString("description"))
                    .instruction(rs.getString("instruction"))
                    .fails(rs.getString("fails"))
                    .type(specialization)
                    .resource(rs.getString("resource"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
